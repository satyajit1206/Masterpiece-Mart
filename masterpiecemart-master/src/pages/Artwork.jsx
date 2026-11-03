import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { api, jsonRequest, imageUrl, money } from '../services/api';
export default function Artwork(){
 const {id}=useParams(); const [product,setProduct]=useState(null),[error,setError]=useState(''),[busy,setBusy]=useState(false);
 useEffect(()=>{api(`/api/allproducts/${id}`).then(setProduct).catch(e=>setError(e.message));},[id]);
 async function add(target){setBusy(true);try {await api(`/api/${target}`,jsonRequest('POST',{...product,productImage:product.imageName,quantity:1}));toast.success(target==='products'?'Added to cart':'Saved to wishlist');}catch(e){toast.error(e.message);}finally{setBusy(false);}}
 return <><Navbar/><main className="mart-shell"><Link to="/AddProductPage">← Back to collection</Link>{error&&<p role="alert" className="mart-error">{error}</p>}{!product&&!error&&<p role="status">Loading artwork…</p>}{product&&<section className="mart-detail"><img src={imageUrl(product.imageName)} alt={product.productName}/><div><p className="mart-eyebrow">THE COLLECTION / {product.artistName}</p><h1>{product.productName}</h1><p className="mart-price">{money(product.price)}</p><p className="mart-lead">{product.productDescription}</p><div className="mart-actions"><button disabled={busy} className="mart-button" onClick={()=>add('products')}>Add to cart</button><button disabled={busy} className="mart-button secondary" onClick={()=>add('wishlist')}>Save to wishlist</button></div><p className="mart-caption">Portfolio demo. Cart and wishlist are shared; checkout is not connected to payments.</p></div></section>}</main><Footer/></>;
}
