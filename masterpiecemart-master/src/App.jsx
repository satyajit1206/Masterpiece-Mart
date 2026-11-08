import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Home from './pages/Home';
import AllProductPage from './pages/AllProductPage';
import Artwork from './pages/Artwork';
import Cart from './pages/Cart';
import Wishlist from './pages/Wishlist';
import AddProduct from './AddProduct';
import Blog from './pages/Blog';
import CreatePost from './pages/CreatePost';
import PostDetail from './pages/PostDetail';
import {About,FAQ} from './pages/Info';
import Account from './pages/Account';
import CheckoutPreview from './pages/CheckoutPreview';
export default function App() {
 return <BrowserRouter><ToastContainer position="top-center" autoClose={2000} hideProgressBar/><Routes>
  <Route path="/" element={<Home/>}/>
  <Route path="/AddProductPage" element={<AllProductPage/>}/>
  <Route path="/artwork/:id" element={<Artwork/>}/>
  <Route path="/Cart" element={<Cart/>}/>
  <Route path="/Wishlist" element={<Wishlist/>}/>
  <Route path="/AddProduct" element={<AddProduct/>}/>
  <Route path="/Blog" element={<Blog/>}/>
  <Route path="/api/posts" element={<CreatePost/>}/>
  <Route path="/api/posts/:id" element={<PostDetail/>}/>
  <Route path="/FAQ" element={<FAQ/>}/><Route path="/AboutUs" element={<About/>}/><Route path="/ContactUs" element={<Account contact/>}/><Route path="/Login" element={<Account/>}/><Route path="/SignUp" element={<Account signup/>}/><Route path="/CheckOut" element={<CheckoutPreview/>}/><Route path="/CheckOut2" element={<CheckoutPreview/>}/>
  <Route path="*" element={<Navigate to="/" replace/>}/>
 </Routes></BrowserRouter>;
}
