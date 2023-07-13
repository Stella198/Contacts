import { useState, useEffect } from 'react';
import ContactService from '../service/ContactService';
import Header from './Header';
import '../../style/ContactApp.css';
export default function ListContactsComponent() {
  const [contacts, setContacts] = useState([]);

  useEffect(() => {
    ContactService.getAllContacts()
      .then((response) => {
        setContacts(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <div className='container-app'>
      <Header />
      <h1>Contact List</h1>
      <table >
        <thead>
          <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            {/* <th>Smoker</th> */}
          </tr>
        </thead>
        <tbody className='contact'>
          {contacts.map((contact) => (
            <tr key={contact.id}>
              <th>{contact.id}</th>
              <td>{contact.firstName}</td>
              <td>{contact.lastName}</td>
              <td>{contact.phoneNumber}</td>
              {/* <td>{contact.smoker? "Yes" : "No"}</td> */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}