import { BASE_URL } from './helper';
export async function api(path, options = {}) {
 const response = await fetch(`${BASE_URL}${path}`, options);
 if (!response.ok) throw new Error(`Request failed (${response.status}). Check that the backend is running.`);
 if(response.status === 204) return null;
 const text = await response.text();
 if(!text) return null;
 try{return JSON.parse(text);}catch{return text;}
}
export const jsonRequest = (method, body) => ({method, headers:{'Content-Type':'application/json'},body:JSON.stringify(body)});
export const imageUrl = name => `${BASE_URL}/api/allproducts/image0/${encodeURIComponent(name)}`;
export const money = value => new Intl.NumberFormat('en-IN',{style:'currency',currency:'INR',maximumFractionDigits:0}).format(value);
