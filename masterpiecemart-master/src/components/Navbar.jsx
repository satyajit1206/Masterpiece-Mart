import { NavLink, Link } from 'react-router-dom';
export default function Navbar() {
 return <header className="mart-nav"><Link className="mart-brand" to="/">Masterpiece<span>Mart</span><small>THE ART MARKETPLACE</small></Link><nav aria-label="Main navigation">{[['/AddProductPage','Collection'],['/Wishlist','Wishlist'],['/Cart','Cart'],['/Blog','Journal'],['/AddProduct','Add artwork']].map(([to,label])=><NavLink key={to} to={to}>{label}</NavLink>)}</nav></header>;
}
