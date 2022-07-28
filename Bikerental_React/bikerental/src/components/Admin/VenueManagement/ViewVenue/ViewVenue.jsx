import { Breadcrumbs, CardActionArea, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Grid, IconButton, TextField, Typography } from '@mui/material';
import Avatar from "@mui/material/Avatar";
import Button from '@mui/material/Button';
import Card from "@mui/material/Card";
import CardActions from '@mui/material/CardActions';
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { deleteVenue, fetchVenues } from '../../../../functions/Admin/VenueManagement/DisplayVenue';
import Header from '../../../Header/Header';
import "./ViewVenue.css";
var backuparr = [];
export default function ViewVenue() {
    const [venue, setVenue] = useState({
        search: '',
        venuedataarr: [],
        isModalVisible: false,
        getId: ''
    });
    const [venues,setVenues]=useState([]);
    const [venueChanged, setVenueChanged] = useState(false)

    let navigate = useNavigate();
   
    const [deleteModalOpen, setDeleteModalOpen] =useState({modal:false,venueId:''});
    const [reset, setReset] = useState(false);
    const breadcrumbs = [
        <Typography key="1" color="inherit">
            Admin
        </Typography>,
        <Typography key="2" color="text.primary">
            Venues
        </Typography>
    ];

    const handleViewVenue = (user) => {
        navigate("/admin/editVenue", { state: { user } })
    }

    useEffect(() => {
        fetchVenues().then((fetchedVenues) => {
            if (venues.length !== fetchedVenues.length) {
                setVenues(fetchedVenues)
            }
            if (venues.length === 0) {
                setVenues(fetchedVenues)
            }
          })
        }, [venues, venueChanged]);

    const handleDeleteVenue = (key) => {
        deleteVenue(key).then(response => {
            setVenueChanged(!venueChanged)
            setDeleteModalOpen(false)
          })
    }
    const handleClickOpen = (vId) => {
        setDeleteModalOpen({...deleteModalOpen,modal:true,venueId:vId});
      };
    
      const handleClose = () => {
        setDeleteModalOpen({...deleteModalOpen,modal:false});
      };
    
    const searchbyname = () => {
        resetdata();
        backuparr = venues;
        console.log(backuparr, "backuparr");
        setReset(true);
        var k = venues;
        console.log(k);
        var q = k.filter((el) => {
            console.log(el.venueName.toLowerCase().includes(venue.search.toLowerCase()), el.venueName.toLowerCase(), venue.search.toLowerCase())
            return el.venueName.toLowerCase().includes(venue.search.toLowerCase())
        })
        console.log(q);
        setVenue({ ...venue, venuedataarr: q });
    }
    const searchbylocation = () => {
        resetdata();
        backuparr = venues;
        console.log(backuparr, "backuparr");
        setReset(true);
        var k = venues;
        var q = k.filter(el => {
            console.log(el.venueLocation.toLowerCase().includes(venue.search.toLowerCase()))
            return el.venueLocation.toLowerCase().includes(venue.search.toLowerCase())
        })
        setVenue({ ...venue, venuedataarr: q });
    }
    const resetdata = () => {
        setReset(false);
        console.log(backuparr, "hello");
        setVenue({ ...venue, venuedataarr: backuparr });
    }
    // const loadsampledata = () => {
    //     setVenue({ ...venue, venuedataarr: venuedata });
    //     AsyncStorage.setItem("venue", JSON.stringify(venuedata));
    // }

    return (
        <div>
            <Header highlight={"Users"} />
                <div className="ViewVenue-Nav">
                    <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
                        <span className="material-icons">person</span>
                    </Avatar>
                    <Breadcrumbs separator="›" aria-label="breadcrumb">
                        {breadcrumbs}
                    </Breadcrumbs>
                </div>
                <div className='ViewVenue-SearchWrapper'>
                <TextField type="text" className='Search-Input'  id="venueName"  label="Enter Venue name" value={venue.venueName}
                        onChange={(e) => { setVenue({ ...venue, search: e.target.value }); }}  variant="standard" size="medium" required/>
                <Button variant="contained" className="ViewVenue-Button"  onClick={searchbyname} disabled={reset} >Search By Venue Name</Button>
                <Button variant="contained" className="ViewVenue-Button" onClick={searchbylocation} disabled={reset} >Search By  Venue Location</Button>
                <Button variant="contained" className="ViewVenue-Button" onClick={resetdata} disabled={!reset} >Reset</Button>
                </div>

                {(venues.length !== 0) ?
                    (
                        <Grid className="d-flex justify-content-center" container spacing={1}> {
                            (reset)?
                    (venue.venuedataarr.map((cards, index) => {
                        return (
                            <Grid item style={{ padding: "20px" }} key={index} id={"adminVenueGrid" + (index + 1)}>
                                <Card style={{ width: 350, borderRadius: "10px", border: "none" }} hoverable="true">
                                    <CardActionArea style={{ height: 350 }}>
                                        <CardMedia
                                            component="img"
                                            style={{ width: 350, height: 200, objectFit: "contain" }}
                                            image={cards.venueImageUrl}
                                            alt="Venue Image"
                                        />
                                        <CardContent>
                                            <Typography gutterBottom variant="h5" component="div">
                                                {cards.venueName}
                                            </Typography>
                                            <Typography variant="body2" color="text.secondary">
                                                {"Place : " + cards.venueLocation}

                                            </Typography>
                                        </CardContent>
                                    </CardActionArea>
                                    <CardActions>
                                        <div>
                                            <IconButton aria-label="edit" onClick={() => handleViewVenue(cards)} id="editVenue">
                                                <span className="material-icons">edit</span>
                                            </IconButton>
                                            <IconButton aria-label="delete" color="error" onClick={() => handleClickOpen(cards.venueId)} id="deleteVenue">
                                                <span className="material-icons">delete</span>
                                            </IconButton>
                                        </div>
                                        {/* <Rating name="read-only" value={cards.rating} readOnly /> */}

                                    </CardActions>
                                </Card>
                            </Grid>
                        );
                    }
                    ))
                        : (
                            venues.map((cards, index) => {
                            return (
                                <Grid item style={{ padding: "20px" }} key={index} id={"adminVenueGrid" + (index + 1)}>
                                    <Card style={{ width: 350, borderRadius: "10px", border: "none" }} hoverable="true">
                                        <CardActionArea style={{ height: 350 }}>
                                            <CardMedia
                                                component="img"
                                                style={{ width: 350, height: 200, objectFit: "contain" }}
                                                image={cards.venueImageUrl}
                                                alt="Venue Image"
                                            />
                                            <CardContent>
                                                <Typography gutterBottom variant="h5" component="div">
                                                    {cards.venueName}
                                                </Typography>
                                                <Typography variant="body2" color="text.secondary">
                                                    {"Place : " + cards.venueLocation}

                                                </Typography>
                                            </CardContent>
                                        </CardActionArea>
                                        <CardActions>
                                            <div>
                                                <IconButton aria-label="edit" onClick={() => handleViewVenue(cards)} id="editVenue">
                                                    <span className="material-icons">edit</span>
                                                </IconButton>
                                                <IconButton aria-label="delete" color="error" onClick={() => handleClickOpen(cards.venueId)} id="deleteVenue">
                                                    <span className="material-icons">delete</span>
                                                </IconButton>
                                            </div>
                                            {/* <Rating name="read-only" value={cards.rating} readOnly /> */}

                                        </CardActions>
                                    </Card>
                                </Grid>
                            );
                        }
                        )
)
                        
                        
                        }
                        </Grid>) : (<div><div className="d-flex justify-content-center"> <h3>No Data to Display</h3></div><div className="d-flex justify-content-center">
                            {/* <Button variant="contained" onClick={loadsampledata} >Load Sample Data</Button> */}
                            </div></div>)}
                <Button onClick={()=> navigate("/admin/addVenue")} variant="contained" size={"large"} id="addVenue" style={{ position: "fixed", right: "3%", bottom: "5%", width: "75px", height: "75px", borderRadius: "50%" }}><span className="material-icons">add</span></Button>
                <Dialog
        open={deleteModalOpen.modal}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Delete Venue"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure? This action cannot be undone.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={()=>handleDeleteVenue(deleteModalOpen.venueId)} color="primary">Yes</Button>
          <Button onClick={handleClose} color="success" autoFocus>
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
        </div>
    )
}