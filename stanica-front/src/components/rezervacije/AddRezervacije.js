import React, { useCallback, useEffect, useState } from 'react';
import { Row, Col, Form, Button , Container} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import LinijaAxios from '../../apis/LinijaAxios';


const AddRezervacije = () => {

    const navigate = useNavigate();
    const[linija,setLinija] = useState([]);
    const[prevoznik, setPrevoznik] = useState([]);
    const[rezervacija, setRezervacija] = useState({
        rDatumRezervacije: "", rDestinacija: "", rBrojMesta: 0, rPrevoznik: null, rLinija: null
    })

    const getLinije = useCallback(()=> {
        LinijaAxios.get("/linije")
            .then(res => {
                console.log(res)
                setLinija(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    },[])

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
    },[])

    const valueInputChange =(e) => {
        const{ name, value} = e.target;
        setRezervacija(prevState => ({
            ...prevState, [name]:value
        }))
    }

    /*const prevoznikSelectionChange = (e) => {
        const value = e.target.value;
        const p = prevoznik.find(p => p.id == value)
        setPrevoznik(prevState => ({
            ...prevState, p
        }))
    }*/

    const prevoznikSelectionChange = (e) => {
        const value = e.target.value;
        const selectedPrevoznik = prevoznik.find(p => p.id == value)
        setRezervacija(prevState => ({
            ...prevState, rPrevoznik: selectedPrevoznik
        }))
    }

    /*const linijaSelectionChange = (e) => {
        const value = e.target.value;
        const line = linija.find(line => line.id == value)
        setLinija(prevState =>  ({
            ...prevState, line
        }))
    }*/

    const linijaSelectionChange = (e) => {
        const value = e.target.value;
        const selectedLine = linija.find(line => line.id == value)
        setRezervacija(prevState => ({
            ...prevState,
            rLinija: selectedLine
        }))
    }

    const create = () => {
        const{rDatumRezervacije, rDestinacija, rBrojMesta, rPrevoznik, rLinija} = rezervacija;
        const params = {
            datumRezervacije: rDatumRezervacije,
            destinacija: rDestinacija,
            brojMesta: rBrojMesta,
            prevoznikId: rPrevoznik.id,
            prevoznikNaziv: rPrevoznik.naziv,
            linijaId: rLinija.id,
            linijaDestinacija: rLinija.destinacija
        }
        LinijaAxios.post("/rezervacije",params)
            .then(res => {
                console.log(res)
                navigate("/linije")
            })
            .catch(error => {
                console.log(error)

            })
        }

    return (
        <Container fluid>
            <h1>Napravi rezervaciju</h1>
            <Row>
                <Col xs={12}>
                    <Form>
                        <Form.Group>
                            <Form.Label>Datum rezervacije</Form.Label>
                            <Form.Control id='rDatumRezervacije' name='rDatumRezervacije' value={rezervacija.rDatumRezervacije} onChange={(e) => valueInputChange(e)} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Destinacija</Form.Label>
                            <Form.Control id='rDestinacija' name='rDestinacija' value={rezervacija.rDestinacija}  onChange={(e) => valueInputChange(e)}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Broj mesta</Form.Label>
                            <Form.Control id='rBrojMesta' name='rBrojMesta' value={rezervacija.rBrojMesta} onChange={(e) => valueInputChange(e)} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Prevoznik</Form.Label>
                            <Form.Control as='select' name='rPrevoznik' value={rezervacija.rPrevoznik ? rezervacija.rPrevoznik.naziv : ""} onChange={prevoznikSelectionChange}>
                                <option>Izaberi</option>{
                                prevoznik.map((p) => (
                                    <option key={p.id} value={p.id}>{p.naziv}</option>
                                ))}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Linija</Form.Label>
                            <Form.Control as='select' name='rLinija' value={rezervacija.rLinija ? rezervacija.rLinija.id : ""} onChange={linijaSelectionChange}>
                                <option>Izaberi</option>{
                                linija.map((line) => (
                                    <option key={line.id} value={line.id}>{line.destinacija}</option>
                                ))}
                            </Form.Control>
                        </Form.Group>
                        <Button onClick={create}>Dodaj</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    )
}


export default AddRezervacije;