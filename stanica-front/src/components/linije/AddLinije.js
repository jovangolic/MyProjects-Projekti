import React, { useCallback, useEffect, useState } from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import LinijaAxios from '../../apis/LinijaAxios';

const AddLinije =() => {

    const navigate = useNavigate();
    const[prevoznik, setPrevoznik] = useState([]);
    const[linija, setLinija] = useState({
        lBrojMesta: 0, lCenaKarte: 0, lDestinacija: "", lVremePolaska: "", lPrevoznik: null
    });

    const getPrevoznike = useCallback(() => {
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
        getPrevoznike();
    },[])

    const valueInputChange = (e) => {
        const {name, value} = e.target;
        setLinija(prevState => ({
            ...prevState, [name]:value
        }))
    }

    const prevoznikSelectionChange = (e) => {
        const value = e.target.value;
        const p = prevoznik.find(p > p.id == value)
        setPrevoznik(prevState => ({
            ...prevState, p
        }))
    }

    const create = () => {
        const {lBrojMesta, lCenaKarte, lDestinacija, lVremePolaska, lPrevoznik} = linija;
        const params = {
            brojMesta: lBrojMesta,
            cenaKarte: lCenaKarte,
            destinacija: lDestinacija,
            vremePolaska: lVremePolaska,
            prevoznikId: lPrevoznik.id,
            prevoznikNaziv: lPrevoznik.naziv
        }
        LinijaAxios.post("/linije",params)
            .then(res => {
                console.log(res)
                navigate("/linije")
            })
            .catch(error => {
                console.log(error)
            })
    }

    return (
        <Row>
            <Col></Col>
            <Col xs='12' sm='12' md='12'>
                <h1>Dodaj liniju</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Broj mesta</Form.Label>
                        <Form.Control id='lBrojMesta' name='lBrojMesta' value={linija.lBrojMesta} onChange={(e) => valueInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Cena karte</Form.Label>
                        <Form.Control id='lCenaKarte' name='lCenaKarte' value={linija.lCenaKarte} onChange={(e) => valueInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Destinacija</Form.Label>
                        <Form.Control id='lDestinacija' name='lDestinacija' value={linija.lDestinacija} onChange={(e) => valueInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Vreme polaska</Form.Label>
                        <Form.Control id='lVremePolaska' name='lVremePolaska' value={linija.lVremePolaska} onChange={(e) => valueInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label htmlFor=''>Prevoznik</Form.Label>
                        <Form.Control as='select' name='lPrevoznik' onChange={prevoznikSelectionChange} value={linija.lPrevoznik ? linija.lPrevoznik.id : ""}>
                            <option>Izaberi</option>
                            {prevoznik.map((p) => (
                                <option key={p.id} value={p.id}>{p.naziv}</option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    <Button onClick={create}>Dodaj</Button>
                </Form>
            </Col>
        </Row>
    )
}

export default AddLinije;