import { ApiClient } from '../../Utils/ApiClient';

async function fetchCompany() {
    var company = []
    await ApiClient.get('/admins')
    .then(response => {
        if (response.data) {
            company = response.data
            return company
        }
    })
    .catch(error => {
        console.log(error)
    })

    return company
}

async function fetchCompanyBikes(id) {
    console.log(id)
    var company = []
    await ApiClient.get('/bike/admin/'+id)
    .then(response => {
        if (response.data) {
            company = response.data
            return company
        }
    })
    .catch(error => {
        console.log(error)
    })

    return company
}


export { fetchCompany, fetchCompanyBikes }