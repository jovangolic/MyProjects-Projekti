import LinijaAxios from '../apis/LinijaAxios.js';
import { jwtDecode } from "jwt-decode"

export const login = async function(username, password){
    const data = {
        username: username,
        password: password
    }
    try{
        const ret = await LinijaAxios.post('korisnici/auth', data);
        const decoded = jwtDecode(ret.data);
        window.localStorage.setItem('role', decoded.role.authority);
        window.localStorage.setItem('jwt', ret.data);
    }catch(error){
        console.log(error);
    }
    window.location.assign("/");
}

export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.localStorage.removeItem('role');
    window.location.assign("/");
}