import { ApiClient } from '../../Utils/ApiClient';

async function editBike(bikeDetails) {

    const data = {
        bikeid: bikeDetails.bikeid,
        bikeno: bikeDetails.bikeno,
        adminid: bikeDetails.adminid,
        model:bikeDetails.model,
        status: bikeDetails.status,
        price: bikeDetails.price,
        type: bikeDetails.type
    }
console.log(data);
    await ApiClient.put('/bike/edit/', data)
    .then(response => {
        if(response.status === 200){
            return true
        }
    })
}

export { editBike }