import { ApiClient } from '../../Utils/ApiClient';

async function addVenue(venueDetails) {

    const data = {
        venueName: venueDetails.venueName,
        venueImageUrl: venueDetails.venueImageUrl,
        venueDescription: venueDetails.venueDescription,
        venueLocation: venueDetails.venueLocation,
        venueCapacity: venueDetails.venueCapacity
    }
    console.log(data);
    await ApiClient.post('/admin/addVenue/', data)
    .then(response => {
        if(response.status === 200){
            return true
        }
    })
}

export { addVenue };
