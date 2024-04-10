import React, { useEffect, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Col, Container, Form, Row, Table } from 'react-bootstrap';
import { jwtDecode } from 'jwt-decode';
import LinijaAxios from '../../apis/LinijaAxios';

const Linije = () => {

    const [linije, setLinije] = useState([]);
    const [prevoznik, setPrevoznik] = useState([]);
    const [pageNo, setPageNo] = useState(0);
    const [pageCount, setPageCount] = useState(0);
    const [searchParams, setSearchParams] = useState({
        destinacija: '',
        prevoznikId: null,
        maksimalnaCena: 0
    });
    //ovo je za prikaz pretrage, zadatak 2.4
    //const [prikaziPretragu, setPrikaziPretragu] = useState(false);
    const token = localStorage.getItem("jwt");
    const decoded = token ? jwtDecode(token) : null;
    const isAdmin = decoded?.role?.authority === "ADMIN";
    const isKorisnik = decoded?.role?.authority === "KORISNIK";
    const navigate = useNavigate();

    const getLinije = () => {
        LinijaAxios.get(`/linije?pageNo=${pageNo}`)
            .then(res => {
                //console.log(res);
                setPageCount(res.headers['total-pages'])
                setLinije(res.data);
            })
            .catch(error => {
                console.log(error)
            })
    }

    const getPrevoznici = useCallback(()=> {
        LinijaAxios.get("/prevoznici")
            .then(res => {
                console.log(res)
                setPrevoznik(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    },[])

    useEffect(()=> {
        getLinije();
        getPrevoznici();
    },[pageNo])

    const goToAdd =() =>{
        navigate("/linije/add")
    }

    const goToEdit =(linijaId) => {
        navigate("/linije/edit/"+linijaId)
    }

    const goToRezervisi = () => {
        navigate('/rezervacije/add');
    }

    const deleteLinijeById = (linijaId) => {
        LinijaAxios.delete("/linije/" + linijaId)
            .then(res => {
                console.log(res)
                alert("Line successfully deleted");
                //getLinije()
            })
            .catch(error => {
                console.log(error)
                alert('Error occurred please try again!');
            })
    }

    const handleChange =(e) => {
        const{ name, value } = e.target;
        const noviObjekat = { ...searchParams, [name]:value};
        setSearchParams(noviObjekat);
    }

    const handleSearch = () => {
        LinijaAxios.get(`/linije`,{
            params: {
                ...searchParams
            }
        })
            .then(res => {
                setLinije(res.data)
            })
            .catch(error => {
                console.log(error)
                alert('Error occurred please try again!');
            })
    }

    

    
    return (
        <Container>
            <h1>Linije</h1>
            
                <Form>
                    <Row>
                        <Col>
                            <Form.Label>Destinacija</Form.Label>
                            <Form.Control  type='text' name='destinacija' onChange={handleChange}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Prevoznik</Form.Label>
                            <Form.Control as='select' name='prevoznikId' onChange={handleChange}>
                                <option value={null}>Izaberi</option>
                                {prevoznik.map((p) => {
                                    return <option key={p.id} value={p.id}>{p.naziv}</option>
                                })}
                            </Form.Control>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                                <Form.Label>Maksimalna Cena</Form.Label>
                                <Form.Control type='number' name='maksimalnaCena' onChange={handleChange} />
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                                <Button className='button button-primary' onClick={handleSearch} >Trazi</Button>
                        </Col>
                    </Row>
                </Form>
            
            <br/>
            {isAdmin ?
                <Button className='button button-navy' onClick={goToAdd}>Dodaj</Button>
                :
                <></>
            }
            <div style={{display: 'flex', justifyContent:'flex-end'}}>
                <Button className='btn btn-info' disabled={pageNo <= 0} onClick={()=> setPageNo(pageNo - 1)} >Back</Button>
                <Button className='btn btn-info' disabled={pageNo >= pageCount - 1} onClick={()=> setPageNo(pageNo + 1)} >Next</Button>
            </div>
            <br/>
            <Table striped bordered hover>
                <thead>
                    <tr className='p-3 mb-2 bg-dark text-white'>
                        <th>Naziv prevoznika</th>
                        <th>Destinacija</th>
                        <th>Broj mesta</th>
                        <th>Vreme polaska</th>
                        <th>Cena karte</th>
                    </tr>
                </thead>
                <tbody>
                    {linije.map((linija) => {
                        return (
                            <tr key={linija.id}>
                                <td>{linija.prevoznikIme}</td>
                                <td>{linija.destinacija}</td>
                                <td>{linija.brojMesta}</td>
                                <td>{linija.vremePolaska}</td>
                                <td>{linija.cenaKarte}</td>
                                <td>
                                    {isKorisnik || isAdmin ? (
                                        <>
                                            <Button className='btn btn-warning' onClick={() => goToRezervisi()}>Rezervisi</Button>
                                            <Button className='btn btn-warning' onClick={() => goToEdit(linija.id)}>Izmeni</Button>
                                        </>
                                    ) : (
                                        <></>
                                    )}
                                    {isAdmin ? 
                                        <Button className='btn btn-danger' onClick={() => deleteLinijeById(linija.id)}>Obrisi</Button> 
                                        : 
                                        <></>
                                    }
                                </td>
                                
                            </tr>
                        );
                    })}
                </tbody>
            </Table>
        </Container>
    )

    
}

export default Linije;