import { useState } from 'react';
import ContactService from '../service/ContactService';
import Header from './Header';
import '../../style/ContactApp.css';

export default function SearchContact() {
  const [lastName, setLastName] = useState('');
  const [contact, setContact] = useState(null);
  const [searchStatus, setSearchStatus] = useState(null);

  const handleChange = (event) => {
    setLastName(event.target.value);
  };

  const handleClick = (event) => {
    event.preventDefault();
    ContactService.getContactsByLastName(lastName)
      .then((response) => {
        if (response.data) {
          console.log(response.data);

          setContact(response.data);
          setSearchStatus({ success: true, message: "Contact found" });
        } else {
          setContact(null);
          setSearchStatus({  success: false, message: "Contact not found"});
        }
      })
      .catch((error) => {
        setContact(null);
        setSearchStatus({ success: false, message: "Error fetching contact" });
      });
  };
  
  return (
    <div className='container-app'>
    <Header />  
      <h1>Search Contact </h1>
      <form onSubmit={handleClick}>
        <label htmlFor="lastName">Last name:</label>
        <input onChange={handleChange} type="text" name="lastName" id="lastName"/>
        <br />
        <button className="button">
          Search Contact
        </button>
      </form>
      {searchStatus && (
        <div className={`search-status ${searchStatus.success ? 'success' : 'error'}`}>
          {searchStatus.message}
        </div>
        )}
      {contact && (
        <div className="contact-details">
          <h2>Contact Details</h2>
            <table>
              <thead>
                <tr>
                  <th>Id</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Phone Number</th>
                  {/* <th>Smoker</th> */}
                </tr>
              </thead>
              <tbody>
                <tr key={contact.id}>
                  <th>{contact.id}</th>
                  <td>{contact.firstName}</td>
                  <td>{contact.lastName}</td>
                  <td>{contact.phoneNumber}</td>
                  {/* <td>{contact.smoker? "Yes" : "No"}</td> */}
               </tr>
              </tbody>
            </table>
          </div>
        )}  
    </div>
  );
}
  
  
