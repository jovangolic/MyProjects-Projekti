import React, { useState, useCallback, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import LinijaAxios from '../../apis/LinijaAxios';

const EditLinije = () => {

    const routeParams = useParams();
    const linijaId = routeParams.id;
    const navigate = useNavigate();
    const[prevoznik, setPrevoznik] = useState([]);
    const[updateLinija, setUpdateLiinja] = useState({
        linijaId: -1,
        linijaBrojMesta: 0,
        linijaCenaKarte: 0,
        linijaDestinacija: "",
        linijaVremePolaska: "",
        linijaPrevoznikId: -1
    })

    const getLinijaById = useCallback(()=> {
        LinijaAxios.get("/linije/"+linijaId)
            .then(res => {
                console.log(res)
                setUpdateLiinja({
                    linijaId: res.data.id,
                    linijaBrojMesta: res.data.brojMesta,
                    linijaCenaKarte: res.data.cenaKarte,
                    linijaDestinacija: res.data.destinacija,
                    linijaVremePolaska: res.data.vremePolaska,
                    linijaPrevoznikId: res.data.prevoznikId
                })
            })
            .catch(error => {
                console.log(error)
                alert('Error occured please try again!');
            })
    },[linijaId])

    const getPrevoznici = useCallback(() =>{
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
        getLinijaById();
        getPrevoznici()
    },[getLinijaById])

    const onBrojMestaChange = (e) => {
        setUpdateLiinja({...updateLinija, linijaBrojMesta: e.target.value})
    }

    const onCenaKarteChange = (e) => {
        setUpdateLiinja({...updateLinija, linijaCenaKarte: e.target.value})
    }

    const onDestinacijaChange = (e) => {
        setUpdateLiinja({...updateLinija, linijaDestinacija: e.target.value})
    }

    const onVremePolaskaChange = (e) => {
        setUpdateLiinja({...updateLinija, linijaVremePolaska: e.target.value})
    }

    const onPrevoznikChange =(e) => {
        const input = e.target;
        setUpdateLiinja(prevState => ({
            ...prevState, linijaPrevoznikId: input.value, linijaPrevoznikNaziv: input.options[input.selectedIndex].text
        }))
    }

    const edit = () => {
        const params = {
            id: routeParams.id,
            brojMesta : updateLinija.linijaBrojMesta,
            cenaKarte: updateLinija.linijaCenaKarte,
            destinacija: updateLinija.linijaDestinacija,
            vremePolaska: updateLinija.linijaVremePolaska,
            prevoznikId: updateLinija.linijaPrevoznikId
        }
        LinijaAxios.put("/linije/" + routeParams.id, params)
            .then(res => {
                console.log(res)
                alert("Line successfully edited");
                navigate("/linije")
            })
            .catch(error => {
                console.log(error)
                alert('Error occured please try again!');
            })
    }

    return (
        <Container fluid>
            <h1>Edit liniju</h1>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor='linijaBrojMesta'>Broj mesta</Form.Label>
                    <Form.Control id='linijaBrojMesta' name='linijaBrojMesta' type='number' value={updateLinija.linijaBrojMesta} onChange={onBrojMestaChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor='linijaCenaKarte'>Cena karte</Form.Label>
                    <Form.Control id='linijaCenaKarte' name='linijaCenaKarte' type='number' value={updateLinija.linijaCenaKarte} onChange={onCenaKarteChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor='linijaDestinacija'>Destinacija</Form.Label>
                    <Form.Control id='linijaDestinacija' name='linijaDestinacija' type='text' value={updateLinija.linijaDestinacija} onChange={onDestinacijaChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor='linijaVremePolaska'>Vreme polaska</Form.Label>
                    <Form.Control id='linijaVremePolaska' name='linijaVremePolaska' type='text' value={updateLinija.linijaVremePolaska} onChange={onVremePolaskaChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor='prevoznik'>Prevoznik</Form.Label>
                    <Form.Control as='select' id='prevoznik' name='prevoznik' value={updateLinija.linijaPrevoznikId} onChange={onPrevoznikChange}>
                        <option></option>
                        {prevoznik.map((p) => (
                            <option key={p.id} value={p.id}>{p.naziv}</option>
                        ))}
                    </Form.Control>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Button className="button button-navy" onClick={edit}>Edit</Button>
                </Col>
            </Row>
        </Container>
    )
}

export default EditLinije;