import React from 'react';
import '../../style/Header.css'
import {Link }from 'react-router-dom';
export default function Header(){
    return(
        <div className="nav">
         <nav className="container">
          <Link className='link' to='/'>ADD CONTACT</Link>
          <Link className='link' to='/contact-list'>CONTACT LIST</Link>
          <Link className='link' to='/search'>SEARCH CONTACT</Link>
          <Link className='link' to='/delete'>DELETE CONTACT</Link>
          </nav>
        </div>   
    )
}