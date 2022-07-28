import { Alert, Avatar, Breadcrumbs, Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Link, TextField, Typography } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addVenue } from '../../../../functions/Admin/VenueManagement/AddVenue';
import Header from '../../../Header/Header';
import "./AddVenue.css";

export default function AddVenue() {
  const [venue, setVenue] = useState({
    venueId: '',
    venueName: '',
    venueImageUrl: '',
    venueDescription: '',
    venueLocation: '',
    venueCapacity: '',
    venueDataarr: []
  });
  const [error, setError] = useState({
    errorMsg: '',
    showError: false
  });
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);

  let navigate = useNavigate();
  const onAddVenue = () => {
    console.log(venue);
      addVenue(venue).then((res) => {
        navigate('/admin/viewVenue')
      })
  }
  const handleOpen = () => {
    if (!(venue.venueImageUrl !== '' && venue.venueName !== '' && venue.venueCapacity !== '' && venue.venueDescription !== '' && venue.venueLocation !== '')) {
      setError({ ...error, showError: true, errorMsg: "Required Fields Missing" })
      setTimeout(() => {
        setError({ ...error, showError: false, errorMsg: '' })
      }, 3000);
    }
    else if(isNaN(venue.venueCapacity))
    {
      setError({ ...error, showError: true, errorMsg: "Venue Capacity value must be a number" })
      setTimeout(() => {
        setError({ ...error, showError: false, errorMsg: '' })
      }, 3000);
    }
    else{
    setDeleteModalOpen(true);
    }
  };

  const handleClose = () => {
    setDeleteModalOpen(false);
  };
  const breadcrumbs = [
    <Typography key="1" color="inherit">
      Admin
    </Typography>,
    <Link
      key="2"
      color="inherit"
      underline="hover"
      onClick={() => { navigate("/admin/viewVenue") }}
    >
      Venues
    </Link>,
    <Typography key="3" color="text.primary">
      Add Venue
    </Typography>
  ];
  return (
    <div>
      <Header highlight="Users" />
      <div className="AddVenue-Nav">
        <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
          <span className="material-icons">person</span>
        </Avatar>
        <Breadcrumbs separator="›" aria-label="breadcrumb">
          {breadcrumbs}
        </Breadcrumbs>
      </div>
      <div className="AddVenue-Form">
        {
          error.showError ? (
            <Alert severity="error" className="Signin-Error">
              Error — <strong>{error.errorMsg}</strong>
            </Alert>
          ) : (
            <div style={{ marginBottom: "45px" }}></div>
          )
        }
        <div className="AddVenue-Title">
          <h1>Add Venue Details</h1>
        </div>
        <TextField type="text" className='AddVenue-Input' id="venueName" label="Enter Venue name" value={venue.venueName}
          onChange={(e) => { setVenue({ ...venue, venueName: e.target.value }); }} variant="standard" required />
        <TextField type="text" className='AddVenue-Input' id="capacityOfVenue" label="Enter the capacity of the venue" value={venue.venueCapacity}
          onChange={(e) => { setVenue({ ...venue, venueCapacity: e.target.value }); }} variant="standard" required />

        <TextField type="text" className='AddVenue-Input' id="imageurl" label="Enter the Venue Image Url" value={venue.venueImageUrl}
          onChange={(e) => { setVenue({ ...venue, venueImageUrl: e.target.value }); }} variant="standard" required />
        <TextField type="text" className='AddVenue-Input' id="venueLocation" label="Enter Venue Location" value={venue.venueLocation}
          onChange={(e) => { setVenue({ ...venue, venueLocation: e.target.value }); }} variant="standard" required />

        <TextField type="text" className='AddVenue-Input' minRows={3} multiline id="venueDescription" label="Enter the Venue Description" value={venue.venueDescription}
          onChange={(e) => { setVenue({ ...venue, venueDescription: e.target.value }); }} variant="standard" required />
        <div className="AddVenue-ButtonWrapper">
          <Button variant="contained" className="AddVenue-Button" id="addVenue" onClick={handleOpen}>Add</Button>
        </div>
      </div>
      <Dialog
        open={deleteModalOpen}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Add Venue"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure to add this?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => { onAddVenue(); }} color="primary">Yes</Button>
          <Button onClick={handleClose} color="success" autoFocus>
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}