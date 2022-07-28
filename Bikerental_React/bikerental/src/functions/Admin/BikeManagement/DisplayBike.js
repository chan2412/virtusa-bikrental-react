import { ApiClient } from '../../Utils/ApiClient';

async function fetchBikes() {
    var bikes = []

    await ApiClient.get('/bikes')
    .then(response => {
        if (response.data) {
            bikes = response.data
            return bikes
        }
    })
    .catch(error => {
        console.log(error)
    })

    return bikes
}


async function deleteBike(bikeID) {
    await ApiClient.delete('/bike/delete/'+bikeID)
    .then(response => {
        if(response.status === 200){
            return true
        }
    })
}

export { fetchBikes, deleteBike };
