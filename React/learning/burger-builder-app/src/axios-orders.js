import axios from 'axios';

const instance = axios.create({
    baseURL: 'gs://my-burger-app-d4fd8.appspot.com'
});

export default instance;