import axios from "axios";

const CONTACT_API_URL="http://localhost:8080";

export default {
  addContact: (contact) => {
    return axios.post(`${CONTACT_API_URL}/contact/add`, contact, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Credentials':'true'
      },
      mode:'no-cors'
    });
  },
  getAllContacts: () => {
    return axios.get(`${CONTACT_API_URL}/contact/getAll`, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Credentials':'true'
      },
      mode:'no-cors'
    });
  },
  getContactsByLastName: (lastName) => {
    return axios.get(`${CONTACT_API_URL}/contact/get/${lastName}`, {
      headers: {
        'Access-Control-Allow-Credentials':'true'
      },
      mode:'no-cors'
    });
  },
  deleteContact: (id) => {
    return axios.delete(`${CONTACT_API_URL}/contact/delete/${id}`, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Credentials':'true'
      },
      mode:'no-cors'
    });
  }
};
