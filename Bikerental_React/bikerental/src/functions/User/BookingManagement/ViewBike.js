import { ApiClient } from '../../Utils/ApiClient';

async function fetchBike(bikeid) {
    var bike;

    await ApiClient.get('/bike/'+bikeid)
    .then(response => {
        if (response.data) {
            bike = response.data
            return bike
        }
    })
    .catch(error => {
        console.log(error)
    })

    return bike
}


export { fetchBike }