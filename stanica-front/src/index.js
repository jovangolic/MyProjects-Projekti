import React from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, Navigate, BrowserRouter as Router, Routes } from 'react-router-dom';
import { Container, Nav, Navbar, Button } from 'react-bootstrap';
import NotFound from './components/NotFound.js';
import Home from './components/Home.js';
import Login from './components/authorization/Login.js';
import { logout } from './services/auth.js';
import Linije from './components/linije/Linije.js';
import AddLinije from './components/linije/AddLinije.js';
import EditLinije from './components/linije/EditLinije.js';
import AddRezervacije from './components/rezervacije/AddRezervacije.js';


const App = () => {

    if (window.localStorage["jwt"]) {
        return (
            <>
                <Router>
                     <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            TEST
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/linije">
                                Linije
                            </Nav.Link>
                            <Button onClick={logout}>Logout</Button>
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/login" element={<Navigate replace to = "/linije" />} />
                            <Route path="/linije" element={<Linije />} />
                            <Route path="/linije/add" element={<AddLinije />} />
                            <Route path="/linije/edit/:id" element={<EditLinije />} />
                            <Route path='/rezervacije/add' element={<AddRezervacije />} />
                            <Route path="*" element={<NotFound />} />
                        </Routes>
                    </Container>
                </Router>
            </>

        );
    } else {
        return (
            <>
             <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            TEST
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/linije">
                                Linije
                            </Nav.Link>
                        </Nav>
                        <Nav>
                            <Nav.Link as={Link} to="/login">
                                Login
                            </Nav.Link>
                        </Nav>
                    </Navbar>
                    
                    <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/linije" element={<Linije />} />
                            <Route path="/login" element={<Login />}/>
                            <Route path="*" element={<Navigate replace to = "/login" />} />
                        </Routes>
                    </Container>
                </Router>
            </>

        )
    }

}

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);
root.render(
    <App/>,
);

