import {useState } from 'react';
import ContactService from '../service/ContactService';
import Header from './Header';
import '../../style/ContactApp.css'

export default function AddContactComponent() {
  const [contact, setContact] = useState({
    firstName: "",
    lastName: "",
    phoneNumber: "",
    // smoker:false
  });
  const [addStatus, setAddStatus] = useState(null); 
  const handleChange = (event) => {
    event.preventDefault();
    const { name,value,type,checked} = event.target;
    setContact((prev) => {
      return {
        ...prev,
        // [name]:type==="checkbox"?checked:value
        [name]:value
      };
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    ContactService.addContact(contact)
    .then((response) => {
      if (response.data.Success) {
        setAddStatus({ success: true, message: "Contact saved successfully!" });
      } else {
        setAddStatus({ success: false, message: "A contact with the same first name and last name already exists." });
      }
    })
    .catch((error) => {
      setAddStatus({ success: false, message: "Error adding contact" });
    });
};
return (
  <div className="container-app">
    <Header />
      <h1>Add Contact</h1>
        <form onSubmit={handleSubmit}>
          <label htmlFor="firstName">First name:</label>
          <input onChange={handleChange} type="text" name="firstName" id="firstName" />

          <label htmlFor="lastName">Last name:</label>
          <input onChange={handleChange} type="text" name="lastName" id="lastName" />

          <label htmlFor="phoneNumber">Phone number:</label>
          <input onChange={handleChange} type="tel" name="phoneNumber" id="phoneNumber" />

          {/* <label htmlFor="smoker">Smoker:</label>
          <input onChange={handleChange} type="checkbox" name="smoker" id="smoker" /> */}
          <br />
          <button className="button">
            Add Contact
          </button>
        </form>
        {addStatus && (
          <div className={`add-status ${addStatus.success ? 'success' : 'error'}`}>
            {addStatus.message}
          </div>
        )}
  </div>
  );
}





