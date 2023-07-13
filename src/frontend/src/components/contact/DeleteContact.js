import { useState} from 'react';
import ContactService from '../service/ContactService';
import Header from './Header';
import '../../style/ContactApp.css';

export default function DealeteContact() {
  const [contactId, setContactId] = useState("");
  const [deleteStatus, setDeleteStatus] = useState(null);

  const handleChange = (event) => {
    event.preventDefault();
    setContactId(event.target.value);
  };

  const handleClick = (event) => {
    event.preventDefault();
    ContactService.deleteContact(contactId)
      .then((response) => {
        if (response.data.Success) {
          setDeleteStatus({ success: true, message: "Contact deleted successfully!" });
        } else {
          setDeleteStatus({ success: false, message:"There is no contact with the specified ID."});
        }
      })
      .catch((error) => {
        setDeleteStatus({ success: false, message: "Error deleting contact" });
      });
  };
  return (
    <div className='container-app'>
    <Header />
    <h1>Delete Contact</h1>
    <form onSubmit={handleClick}>
      <label htmlFor="contactId">Contact ID:</label>
      <input onChange={handleChange} type="text" name="contactId" id="contactId" />
      <br />
      <button className="button">
        Delete Contact
      </button>
    </form>
    {deleteStatus && (
      <div className={`delete-status ${deleteStatus.success ? 'success' : 'error'}`}>
        {deleteStatus.message}
      </div>
      )}     
     </div>
   );
  }

