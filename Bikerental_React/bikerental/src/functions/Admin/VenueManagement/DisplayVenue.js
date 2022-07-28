import { ApiClient } from '../../Utils/ApiClient';

async function fetchVenues() {
    var venues = []

    await ApiClient.get('/admin/getVenue')
        .then(response => {
            if (response.data) {
                venues = response.data
                return venues
            }
        })
        .catch(error => {
            console.log(error)
        })

    return venues
}


async function deleteVenue(venueId) {
    console.log(venueId);
    await ApiClient.post('/admin/deleteVenue/' + venueId)
        .then(response => {
            if (response.status === 200) {
                return true
            }
        })
}

export { fetchVenues, deleteVenue };
