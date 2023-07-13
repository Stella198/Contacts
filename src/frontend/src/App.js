import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,    
} from "react-router-dom";
import AddContactComponent from './components/contact/AddContactComponent';
import ListContactComponent from './components/contact/ListContactsComponent';
import SearchContact from './components/contact/SerachContact';
import DealeteContact from './components/contact/DeleteContact';
function App() {
  return (
    <Router>
      <Routes>
        <Route exact path='/' element={< AddContactComponent/>}/>
        <Route exact path='/contact-list' element={<ListContactComponent/>}/>
        <Route exact path='/search' element={<SearchContact/>}/>
        <Route exact path='/delete' element={<DealeteContact/>}/>

      </Routes>
    </Router>
  );
}
export default App;
