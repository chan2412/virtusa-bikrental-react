import { AsyncStorage } from 'AsyncStorage';
import axios from 'axios';

const storageKey = 'jwt';

// export const ApiClient = axios.create({
//     baseURL: 'https://' + window.location.hostname.replace('8081', '8080')
// });

export const ApiClient = axios.create({
    baseURL: 'http://localhost:8080'
});

export function doUrlEncodedRequest(method, params, url) {
    const data = Object.keys(params).map((key) => `${key}=${encodeURIComponent(params[key])}`).join('&');
    
    return {
        method,
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
        data,
        url
    }
}

export async function setAuthorizationHeader(jwt) {
    console.log("Setting JWT")
    await AsyncStorage.setItem(storageKey, jwt);
    ApiClient.defaults.headers['Authorization'] = `Bearer ${jwt}`;
    console.log(ApiClient.defaults)
}

export function resetAuthorizationHeader() {
    AsyncStorage.setItem(storageKey);
    delete ApiClient.defaults.headers['Authorization'];
}

export function loadAuthorizationHeaderFromStorage() {
    const jwt = AsyncStorage.getItem(storageKey);
    if (jwt) {
        ApiClient.defaults.headers['Authorization'] = `Bearer ${jwt}`;
    }
}