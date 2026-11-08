import Navbar from './Navbar';
import Footer from './Footer';
export default function Page({title,children}){return <><Navbar/><main className="mart-shell"><div className="mart-page-heading"><p className="mart-eyebrow">MASTERPIECE MART</p><h1>{title}</h1></div>{children}</main><Footer/></>}
