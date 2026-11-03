import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { api, imageUrl, money } from '../services/api';
export default function Collection(){
 const [products,setProducts]=useState([]),[query,setQuery]=useState(''),[error,setError]=useState(''),[loading,setLoading]=useState(true);
 useEffect(()=>{api('/api/allproducts').then(setProducts).catch(e=>setError(e.message)).finally(()=>setLoading(false));},[]);
 const filtered=products.filter(p=>`${p.productName} ${p.artistName}`.toLowerCase().includes(query.toLowerCase()));
 return <><Navbar/><main className="mart-shell"><div className="mart-page-heading"><p className="mart-eyebrow">THE COLLECTION</p><h1>A piece for every perspective.</h1><p>Discover artwork, meet its creator, and make room for something beautiful.</p></div><div className="mart-toolbar"><span>{filtered.length} artworks</span><input aria-label="Search artwork or artist" placeholder="Search artwork or artist…" value={query} onChange={e=>setQuery(e.target.value)}/></div>{loading&&<p role="status">Loading the collection…</p>}{error&&<p className="mart-error" role="alert">{error}</p>}<div className="mart-grid">{filtered.map(p=><Link className="mart-card" key={p.productId} to={`/artwork/${p.productId}`}><div><img src={imageUrl(p.imageName)} alt={p.productName}/></div><small>{p.artistName}</small><h2>{p.productName}</h2><p>{money(p.price)} <span>View artwork ↗</span></p></Link>)}</div>{!loading&&!error&&!filtered.length&&<p className="mart-empty">No artwork found. Try another search or add the first piece.</p>}</main><Footer/></>;
}
