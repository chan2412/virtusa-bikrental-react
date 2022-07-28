import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import IconButton from '@mui/material/IconButton';
import Paper from '@mui/material/Paper';
import { useTheme } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableFooter from '@mui/material/TableFooter';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import { AsyncStorage } from 'AsyncStorage';
import 'material-icons/iconfont/material-icons.css';
import PropTypes from 'prop-types';
import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { deleteBike } from '../../../../functions/Admin/BikeManagement/DisplayBike';
import { fetchCompanyBikes } from '../../../../functions/User/BookingManagement/DisplayCompany';
import Header from '../../../Header/Header';
import './DisplayBike.css';




function TablePaginationActions(props) {
  const theme = useTheme();
  const { count, page, rowsPerPage, onPageChange } = props;

  const handleFirstPageButtonClick = (event) => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event) => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event) => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event) => {
    onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
  };

  return (
    <Box sx={{ flexShrink: 0, ml: 2.5 }}>
      <IconButton
        onClick={handleFirstPageButtonClick}
        disabled={page === 0}
        aria-label="first page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">last_page</span> : <span className="material-icons">first_page</span>}
      </IconButton>
      <IconButton
        onClick={handleBackButtonClick}
        disabled={page === 0}
        aria-label="previous page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">keyboard_arrow_right</span> : <span className="material-icons">keyboard_arrow_left</span>}
      </IconButton>
      <IconButton
        onClick={handleNextButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="next page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">keyboard_arrow_left</span> : <span className="material-icons">keyboard_arrow_right</span>}
      </IconButton>
      <IconButton
        onClick={handleLastPageButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="last page"
      >
        {theme.direction === 'rtl' ? <span className="material-icons">first_page</span> : <span className="material-icons">last_page</span>}
      </IconButton>
    </Box>
  );
}

TablePaginationActions.propTypes = {
  count: PropTypes.number.isRequired,
  onPageChange: PropTypes.func.isRequired,
  page: PropTypes.number.isRequired,
  rowsPerPage: PropTypes.number.isRequired,
};

function DisplayBike() {

  const [users, setUsers] = useState([])
  const [bikeID, setBikeID] = useState()
  const [userChanged, setUserChanged] = useState(false)
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [deleteModalOpen, setDeleteModalOpen] = React.useState(false);
  let navigate = useNavigate();

  const breadcrumbs = [
    <Typography key="1" color="inherit">
      Admin
    </Typography>,
    <Typography key="2" color="text.primary">
      Bikes
    </Typography>
  ];

  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - users.length) : 0;

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleClickOpen = (bikeID) => {
    setDeleteModalOpen(true);
    setBikeID(bikeID)
  };

  const handleClose = () => {
    setDeleteModalOpen(false);
  };

  const handleEditBike = (bike) => {
    navigate('/admin/editBike', { state: { bike: bike } })
  }

  const handleDeleteBike = () => {
    deleteBike(bikeID).then(response => {
      setUserChanged(!userChanged)
      setDeleteModalOpen(false)
    })
  }

  useEffect(() => {
    var email;
    AsyncStorage.getItem('USER').then(
      res=>{
        var x=JSON.parse(res);
        email=x.user;
        fetchCompanyBikes(email).then((fetchedUsers) => {
          if (users.length !== fetchedUsers.length) {
            setUsers(fetchedUsers)
          }
          if (users.length === 0) {
            setUsers(fetchedUsers)
          }
        })
      }
    );
    
  }, [])

  return (
    <div>
      <Header highlight={"Bikes"} />
      <div className="DisplayBike-Nav">
        <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
          <span className="material-icons">person</span>
        </Avatar>
        <Breadcrumbs separator="›" aria-label="breadcrumb">
          {breadcrumbs}
        </Breadcrumbs>
      </div>
      <TableContainer className="DisplayBike-Table" component={Paper}>
        <Table sx={{ minWidth: 500 }} aria-label="custom pagination table">
          <TableHead>
            <TableRow>
             
              <TableCell align="right">Status</TableCell>
              <TableCell align="right">Model</TableCell>
              <TableCell align="right">Price</TableCell>
              <TableCell align="right">Type</TableCell>
              <TableCell align="right">Edit&nbsp;&nbsp;</TableCell>
              <TableCell align="right">Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {(rowsPerPage > 0
              ? users.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              : users
            ).map((row) => (
              <TableRow key={row.bikeid}>
                
                <TableCell style={{ width: 160 }} align="right">
                  {row.status}
                </TableCell>
                <TableCell style={{ width: 160 }} align="right">
                  {row.model}
                </TableCell>
                <TableCell style={{ width: 160 }} align="right">
                  {'$'+row.price}
                </TableCell>
                <TableCell style={{ width: 160 }} align="right">
                  {row.type}
                </TableCell>
                <TableCell style={{ width: 50 }} align="right">
                  <IconButton aria-label="edit" onClick={() => handleEditBike(row)}>
                    {/* <EditIcon sx={{fontSize: '20px'}} /> */}
                    <span className="material-icons">edit</span>
                  </IconButton>
                </TableCell>
                <TableCell style={{ width: 50 }} align="right">
                  <IconButton aria-label="delete" color="error" onClick={() => handleClickOpen(row.bikeid)}>
                    {/* <DeleteIcon sx={{fontSize: '20px'}}/> */}
                    <span className="material-icons">delete</span>
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}

            {emptyRows > 0 && (
              <TableRow style={{ height: 53 * emptyRows }}>
                <TableCell colSpan={6} />
              </TableRow>
            )}
          </TableBody>
          <TableFooter>
            <TableRow>
              <TablePagination
                rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
                colSpan={6}
                count={users.length}
                rowsPerPage={rowsPerPage}
                page={page}
                SelectProps={{
                  inputProps: {
                    'aria-label': 'rows per page',
                  },
                  native: true,
                }}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
                ActionsComponent={TablePaginationActions}
              />
            </TableRow>
          </TableFooter>
        </Table>
      </TableContainer>
      
      <Button onClick={() => navigate("/admin/addBike")} variant="contained" size={"large"} id="addBike" style={{ position: "fixed", right: "3%", bottom: "5%", width: "75px", height: "75px", borderRadius: "50%" }}><span className="material-icons">add</span></Button>
      <Dialog
        open={deleteModalOpen}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Delete User"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure? This action cannot be undone.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => handleDeleteBike()} color="primary">Yes</Button>
          <Button onClick={handleClose} color="success" autoFocus>
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}

export default DisplayBike