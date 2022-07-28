
import { ApiClient } from '../../Utils/ApiClient';

async function fetchProfile() {
    var profile={};
    var value=JSON.parse(localStorage.getItem("@AsyncStorage:USER"));
    await ApiClient.get('/admin/'+value.user).then(response => {
        if (response.data) {
            profile = response.data
            return profile
        }
    })
    .catch(error => {
        console.log(error)
    return profile
        });
        return profile;
    }


export { fetchProfile }