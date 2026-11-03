package com.rest.product.restproduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mock.web.MockMultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("demo")
@Transactional
class MarketplaceApiTests {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test void emptyCollectionsAreSuccessfulArrays() throws Exception {
        for (String path : new String[]{"/api/products", "/api/wishlist", "/api/allproducts"})
            mvc.perform(get(path)).andExpect(status().isOk()).andExpect(content().json("[]"));
    }
    @Test void cartLifecyclePreservesIdentityAndDescription() throws Exception {
        String body = "{\"productId\":71,\"productName\":\"Study\",\"artistName\":\"Artist\",\"price\":1200,\"quantity\":1,\"productDescription\":\"Original artwork\"}";
        mvc.perform(post("/api/products").contentType("application/json").content(body)).andExpect(status().isCreated());
        mvc.perform(put("/api/products/71").contentType("application/json").content(body.replace("\"productId\":71", "\"productId\":99").replace("\"quantity\":1", "\"quantity\":2"))).andExpect(status().isNoContent());
        mvc.perform(get("/api/products/71")).andExpect(status().isOk()).andExpect(jsonPath("$.quantity").value(2)).andExpect(jsonPath("$.productDescription").value("Original artwork"));
        mvc.perform(delete("/api/products/71")).andExpect(status().isNoContent());
        mvc.perform(get("/api/products/71")).andExpect(status().isNotFound());
        mvc.perform(put("/api/products/71").contentType("application/json").content(body)).andExpect(status().isNotFound());
    }
    @Test void wishlistLifecycle() throws Exception {
        mvc.perform(post("/api/wishlist").contentType("application/json").content("{\"productId\":42,\"productName\":\"Study\",\"price\":1200}")).andExpect(status().isCreated());
        mvc.perform(get("/api/wishlist/42")).andExpect(status().isOk()).andExpect(jsonPath("$.productName").value("Study"));
        mvc.perform(delete("/api/wishlist/42")).andExpect(status().isNoContent());
        mvc.perform(get("/api/wishlist/42")).andExpect(status().isNotFound());
    }
    @Test void uploadAndRetrieveArtwork() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(new BufferedImage(4, 4, BufferedImage.TYPE_INT_RGB), "jpg", out);
        byte[] bytes = out.toByteArray();
        mvc.perform(multipart("/api/allproducts")
            .file(new MockMultipartFile("file0", "a.jpg", "image/jpeg", bytes))
            .file(new MockMultipartFile("file1", "b.jpg", "image/jpeg", bytes))
            .file(new MockMultipartFile("file2", "c.jpg", "image/jpeg", bytes))
            .param("productName", "Study").param("artistName", "Artist").param("artistId", "1").param("price", "1200"))
            .andExpect(status().isOk());
        mvc.perform(get("/api/allproducts")).andExpect(status().isOk()).andExpect(jsonPath("$[0].productName").value("Study")).andExpect(jsonPath("$[0].data0").doesNotExist());
        mvc.perform(get("/api/allproducts/image0/Study")).andExpect(status().isOk()).andExpect(content().bytes(bytes));
        mvc.perform(get("/api/allproducts/image0/missing")).andExpect(status().isNotFound());
    }
    @Test void postAndCommentFlow() throws Exception {
        String result = mvc.perform(post("/api/posts").contentType("application/json").content("{\"name\":\"Art story\",\"postedBy\":\"Demo\",\"content\":\"A study of light\",\"tags\":[\"art\"]}"))
            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        long id = mapper.readTree(result).get("id").asLong();
        mvc.perform(post("/api/comments/create").param("postId", String.valueOf(id)).param("postedBy", "Visitor").param("content", "Great story"))
            .andExpect(status().isOk());
        mvc.perform(get("/api/comments/" + id)).andExpect(status().isOk()).andExpect(jsonPath("$[0].content").value("Great story"));
        mvc.perform(put("/api/posts/" + id + "/like")).andExpect(status().isOk());
        mvc.perform(get("/api/posts/" + id)).andExpect(status().isOk()).andExpect(jsonPath("$.likeCount").value(1));
    }
    @Test void localFrontendPreflightWorks() throws Exception {
        mvc.perform(options("/api/products").header("Origin", "http://localhost:5173").header("Access-Control-Request-Method", "PUT"))
            .andExpect(status().isOk()).andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5173"));
    }
}
