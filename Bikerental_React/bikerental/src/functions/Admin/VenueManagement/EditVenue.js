import { ApiClient } from '../../Utils/ApiClient';

async function editVenue(venueDetails) {

    const data = {
        venueName: venueDetails.venueName,
        venueImageUrl: venueDetails.venueImageUrl,
        venueDescription: venueDetails.venueDescription,
        venueLocation: venueDetails.venueLocation,
        venueCapacity: venueDetails.venueCapacity
    }
    console.log(data);
    await ApiClient.post('/admin/editVenue/' + venueDetails.venueId, data)
        .then(response => {
            if (response.status === 200) {
                return true
            }
        })
}

export { editVenue };
