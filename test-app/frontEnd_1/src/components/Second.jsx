import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import { Link } from 'react-router-dom';
class Second extends Component {
  state = {
    file: '',
    cras: [],
    docs: [],
    newCra: {
      start_date: '',
      end_date: '',
      month: '',
      working_days_count: '',
      document: { document_name: '', document_location: '', type: '' }

    },

    editCra: {
      id: '',
      start_date: '',
      end_date: '',
      month: '',
      working_days_count: '',
      createdAt: '',
      updatedAt: '',
      document: { document_name: '', document_location: '', type: '' },

    },
    formErrors: { start_date: '', end_date: '', month: '', working_days_count: '' },
    dateWarn: { msg: '' },
    path: 'C:\\Users\\LENOVO\\cra-app\\public\\docs\\',
    newCraModal: false,
    editcramodal: false,
    viewcramodal: false,
    handleError: false
  }

  onFileChange = (event) => {
    this.setState({
      file: event.target.files[0]
    });
    console.warn("file:", event.target.files[0]);
  }

  getCurrentDate() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0');
    var yyyy = today.getFullYear();
    return yyyy + '-' + mm + '-' + dd;
  }

  componentWillMount() {
    this._refreshcras();
    console.log("sqtqtte",this.state["newCra"])
  }
  toggleNewCraModal() {
    this.setState({
      newCraModal: !this.state.newCraModal ,
      formErrors: {start_date: '',end_date: '',month: '',working_days_count: ''} ,
      dateWarn: {msg:''}
      
    });
  }
  toggleViewCramodal() {

    this.setState({
      viewcramodal: !this.state.viewcramodal,
      formErrors: {start_date: '',end_date: '',month: '',working_days_count: ''} ,
      dateWarn: {msg:''}
    });
  }
  toggleeditcramodal() {

    this.setState({
      editcramodal: !this.state.editcramodal
    });
  }

  verifyForm(val) {
    let { formErrors } = this.state;

    if (!this.state[val].start_date) {
      formErrors.start_date = 'this field is required';

    }

    if (!this.state[val].end_date) {
      formErrors.end_date = 'this field is required';

    }

    if (!this.state[val].month) {
      formErrors.month = 'this field is required';

    }
    if (!this.state[val].working_days_count) {
      formErrors.working_days_count = 'this field is required';

    }
    this.setState({ formErrors });

    if (Object.values(formErrors).every(function (e) { return e == ""; }))
return true ;
    //  this.setState({ handleError: true });
  }
  warnConfirm() {
    if (!this.state.dateWarn.msg)
      return true;
    else if (window.confirm("Are you sure to insert a past date ?"))
      return true;
    else return false;
  }
  addcras() {
    //this.verifyForm("newCra");

    if (  this.verifyForm("newCra")  && this.warnConfirm()) {

      const data = new FormData();
      data.append('file', this.state.file);
      if (this.state.file.name) {
        this.state.newCra.document.document_name = this.state.file.name;
        this.state.newCra.document.document_location = this.state.path + this.state.file.name;
      }

      axios.post('http://localhost:8080/cras/upload', data)

        ;

      console.log(this.state.newCra.document.type);

      axios.post('http://localhost:8080/cras/', this.state.newCra).then((response) => {



        this._refreshcras();
        this.setState({
          newCraModal: false, newCra: {
            start_date: '',
            end_date: '',
            month: '',
            working_days_count: '',
            document: { document_name: '', document_location: '', type: '' },
            file: '',
            handleError: false,

          }
        });
      });
    }
  }
  updateCra() {
   

    if ( this.verifyForm("editCra") && this.warnConfirm()) {

    const data = new FormData();
    data.append('file', this.state.file);
    if (this.state.file.name != undefined) {
      this.state.editCra.document.document_name = this.state.file.name;
      this.state.editCra.document.document_location = this.state.path + this.state.file.name;
      axios.post('http://localhost:8080/cras/upload', data);
    }
    axios.put('http://localhost:8080/cras/update/' + this.state.editCra.id,
      this.state.editCra
    ).then((response) => {
      this._refreshcras();

      this.setState({
        editcramodal: false, editCra: { id: '', start_date: '', end_date: '', month: '', working_days_count: '', createdAt: '', document: { document_name: '', document_location: '', type: '' } }
      })
    });
  }
  }

  editCra(id, start_date, end_date, month, working_days_count, createdAt, document) {
    this.setState({
      editCra: { id, start_date, end_date, month, working_days_count, createdAt, document }, editcramodal: !this.state.editcramodal, file: ''
    });
  }
  viewCra(id, start_date, end_date, month, working_days_count, createdAt, updatedAt, document) {
    this.setState({
      editCra: { id, start_date, end_date, month, working_days_count, createdAt, updatedAt, document }, viewcramodal: !this.state.viewcramodal
    });
  }
  deleteCra(id) {
    if (window.confirm("Are you sure ?")) {
      axios.delete('http://localhost:8080/cras/' + id).then((response) => {
        this._refreshcras();
      });
    }
  }
  _refreshcras() {
    axios.get('http://localhost:8080/cras').then((response) => {
      this.setState({
        cras: response.data, file: '', handleError: false, dateWarn: { msg: '' } 
        , formErrors: {start_date: '',end_date: '',month: '',working_days_count: ''} 
      })
    });
  }
  render() {
    let cras = this.state.cras.map((cra) => {
      return (
        <tr key={cra.id}>

          <td>{cra.start_date}</td>
          <td>{cra.end_date}</td>
          {(cra.month != 0) ? <td>{cra.month}</td> : <td> </td>}
          {(cra.working_days_count != 0) ? <td>{cra.working_days_count}</td> : <td> </td>}


          <td>
            <Button color="success" size="sm" className="mr-2" onClick={this.viewCra.bind(this, cra.id, cra.start_date, cra.end_date, cra.month, cra.working_days_count, cra.createdAt, cra.updatedAt, cra.document)}>Details</Button>
            <Button color="primary" size="sm" className="mr-2" onClick={this.editCra.bind(this, cra.id, cra.start_date, cra.end_date, cra.month, cra.working_days_count, cra.createdAt, cra.document)}>Edit</Button>
            <Button color="danger" size="sm" onClick={this.deleteCra.bind(this, cra.id)}>Delete</Button>

          </td>
        </tr>
      )
    });
    return (
      <div className="App container">



        <Button className="my-3" color="primary" onClick={this.toggleNewCraModal.bind(this)}>Add cra</Button>

        <Modal isOpen={this.state.newCraModal} toggle={this.toggleNewCraModal.bind(this)}>
          <ModalHeader toggle={this.toggleNewCraModal.bind(this)}>Add a new cra</ModalHeader>
          <ModalBody>
            <FormGroup>
              <Label for="start_date">start date</Label>
              <Input type="date" id="start_date" value={this.state.newCra.start_date} onChange={(e) => {
                let { newCra } = this.state;
                let { formErrors } = this.state;
                let { dateWarn } = this.state;
                newCra.start_date = e.target.value;
                this.setState({ newCra });
                (this.state.newCra.start_date && this.state.newCra.end_date &&
                  this.state.newCra.end_date > this.state.newCra.start_date) ? formErrors.end_date = ""
                  : (this.state.newCra.end_date && this.state.newCra.start_date) ? formErrors.end_date = "end date is greater than start date!"
                    : formErrors.end_date = formErrors.start_date = "";
                this.setState({ formErrors });
                console.warn("startdate", this.state.newCra.start_date);
                console.warn("currentdate", this.getCurrentDate());
                (this.state.newCra.start_date && this.state.newCra.start_date < this.getCurrentDate()) ? dateWarn.msg = "you have inserted a past date!"
                  : dateWarn.msg = "";
                this.setState({ dateWarn });
              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.start_date}</span>
              <span className="text-warning">{this.state.dateWarn.msg}</span>
            </FormGroup>
            <FormGroup>
              <Label for="end_date">end_date</Label>
              <Input type="date" id="end_date" value={this.state.newCra.end_date} onChange={(e) => {
                let { newCra } = this.state;
                let { formErrors } = this.state;
                newCra.end_date = e.target.value;
                this.setState({ newCra });
                (this.state.newCra.start_date && this.state.newCra.end_date &&
                  this.state.newCra.end_date > this.state.newCra.start_date) ? formErrors.end_date = ""
                  : (this.state.newCra.start_date && this.state.newCra.end_date) ? formErrors.end_date = "end date is greater than start date!"
                    : formErrors.end_date = formErrors.start_date = "";
                this.setState({ formErrors });

              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.end_date}</span>
            </FormGroup>

            <FormGroup>
              <Label for="month">month</Label>
              <Input id="month" value={this.state.newCra.month} onChange={(e) => {
                let { newCra } = this.state;
                let { formErrors } = this.state;
                newCra.month = e.target.value;
                this.setState({ newCra });
                (this.state.newCra.month && 0 < Number(this.state.newCra.month) && Number(this.state.newCra.month < 13)) ? formErrors.month = ""
                  : (this.state.newCra.month) ? formErrors.month = "please insert a valid month" : formErrors.month = "";
                this.setState({ formErrors });
              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.month}</span>
            </FormGroup>

            <FormGroup>
              <Label for="working_days_count">working_days_count</Label>
              <Input id="working_days_count" value={this.state.newCra.working_days_count} onChange={(e) => {
                let { newCra } = this.state;
                let { formErrors } = this.state;
                newCra.working_days_count = e.target.value;
                this.setState({ newCra });
                (this.state.newCra.working_days_count && 0 < Number(this.state.newCra.working_days_count) && Number(this.state.newCra.working_days_count < 32)) ? formErrors.working_days_count = ""
                  : (this.state.newCra.working_days_count) ? formErrors.working_days_count = "please insert a valid working days count"
                    : formErrors.working_days_count = "";
                this.setState({ formErrors });

              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.working_days_count}</span>
            </FormGroup>
            <FormGroup>
              <Label for="file">file</Label>
              <Input type="file" id="file" onChange={this.onFileChange} />
            </FormGroup>
            <FormGroup>
              <Label for="type">file type </Label><br />
              <select name="types" onChange={(e) => {
                let { newCra } = this.state;

                newCra.document.type = e.target.value;


                this.setState({ newCra });

              }} >
                <option disabled selected value> -- select type -- </option>
                <option value={this.state.newCra.type}>pdf</option>
                <option value={this.state.newCra.type}>doc</option>
                <option value={this.state.newCra.type}>txt</option>
                <option value={this.state.newCra.type}>png</option>
                <option value={this.state.newCra.type}>jpg</option>

              </select>

            </FormGroup>

          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.addcras.bind(this)}>Add cra</Button>{' '}
            <Button color="secondary" onClick={this.toggleNewCraModal.bind(this)}>Cancel</Button>
          </ModalFooter>
        </Modal>
        <Modal isOpen={this.state.viewcramodal} toggle={this.toggleViewCramodal.bind(this)}>
          <ModalHeader toggle={this.toggleViewCramodal.bind(this)}>Detail</ModalHeader>
          <ModalBody>

            <ul>
              <li className="list list-group-item">Start date: <strong>{this.state.editCra.start_date}</strong> </li>
              <li className="list list-group-item">End date: <strong>{this.state.editCra.end_date}</strong> </li>
              {this.state.editCra.month ? <li className="lit list-group-item">Month: <strong>{this.state.editCra.month}</strong> </li> : <li className="lit list-group-item">Month: </li>}
              {this.state.editCra.working_days_count ? <li className="lit list-group-item">working_days_count: <strong>{this.state.editCra.working_days_count}</strong> </li> : <li className="lit list-group-item">working_days_count: </li>}
              <li className="list list-group-item">Created At: <strong>{this.state.editCra.createdAt}</strong> </li>
              <li className="list list-group-item">Updated at: <strong>{this.state.editCra.updatedAt}</strong> </li>
              {!this.state.editCra.document.document_name ? <li className="list list-group-item">Document: <strong>Not Available</strong></li> :
                <li className="list list-group-item">Document : <strong> <Link to={"/docs/" + this.state.editCra.document.document_name} target="_blank" download><Button color="primary" >Download</Button></Link> </strong> </li>
              }

            </ul>
          </ModalBody>
          <ModalFooter>

            <Button color="secondary" onClick={this.toggleViewCramodal.bind(this)}>Ok</Button>
          </ModalFooter>




        </Modal>

        <Modal isOpen={this.state.editcramodal} toggle={this.toggleeditcramodal.bind(this)}>
          <ModalHeader toggle={this.toggleeditcramodal.bind(this)}>Edit cra</ModalHeader>
          <ModalBody>
            <FormGroup>
              <Label for="start_date">start_date</Label>
              <Input type="date" id="start_date" value={this.state.editCra.start_date} onChange={(e) => {
                let { editCra } = this.state;
                let { formErrors } = this.state;
                let { dateWarn } = this.state;
                editCra.start_date = e.target.value;
                this.setState({ editCra });
                (this.state.editCra.start_date && this.state.editCra.end_date &&
                  this.state.editCra.end_date > this.state.editCra.start_date) ? formErrors.end_date = ""
                  : (this.state.editCra.end_date && this.state.editCra.start_date) ? formErrors.end_date = "end date is greater than start date!"
                    : formErrors.end_date = formErrors.start_date = "";
                this.setState({ formErrors });
                console.warn("startdate", this.state.editCra.start_date);
                console.warn("currentdate", this.getCurrentDate());
                (this.state.editCra.start_date && this.state.editCra.start_date < this.getCurrentDate()) ? dateWarn.msg = "you have inserted a past date!"
                  : dateWarn.msg = "";
                this.setState({ dateWarn });
              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.start_date}</span>
              <span className="text-warning">{this.state.dateWarn.msg}</span>
            </FormGroup>

            <FormGroup>
              <Label for="end_date">end_date</Label>
              <Input type="date" id="end_date" value={this.state.editCra.end_date} onChange={(e) => {
                let { editCra } = this.state;
                let {formErrors} = this.state;
                editCra.end_date = e.target.value;
                this.setState({ editCra });
                 (this.state.editCra.start_date && this.state.editCra.end_date &&
                  this.state.editCra.end_date > this.state.editCra.start_date) ? formErrors.end_date = ""
                  : (this.state.editCra.start_date && this.state.editCra.end_date) ? formErrors.end_date = "end date is greater than start date!"
                    : formErrors.end_date = formErrors.start_date = "";
                this.setState({ formErrors });

              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.end_date}</span>
            </FormGroup>
            <FormGroup>
              <Label for="month">month</Label>
              <Input id="month" value={this.state.editCra.month} onChange={(e) => {
                let { editCra } = this.state;
                let { formErrors } = this.state;
                editCra.month = e.target.value;
                (this.state.editCra.month && 0 < Number(this.state.editCra.month) && Number(this.state.editCra.month < 13)) ? formErrors.month = ""
                  : (this.state.editCra.month) ? formErrors.month = "please insert a valid month" : formErrors.month = "";
                this.setState({ formErrors });
                this.setState({ editCra });
                
              }} />
         <span style={{ color: "red" }}>{this.state.formErrors.month}</span>

            </FormGroup>
            <FormGroup>
              <Label for="working_days_count">working_days_count</Label>
              <Input id="working_days_count" value={this.state.editCra.working_days_count} onChange={(e) => {
                let { editCra } = this.state;
                let { formErrors } = this.state;
                editCra.working_days_count = e.target.value;
                this.setState({ editCra });                
                (this.state.editCra.working_days_count && 0 < Number(this.state.editCra.working_days_count) && Number(this.state.editCra.working_days_count < 32)) ? formErrors.working_days_count = ""
                  : (this.state.editCra.working_days_count) ? formErrors.working_days_count = "please insert a valid working days count"
                    : formErrors.working_days_count = "";
                this.setState({ formErrors });
              }} />
              <span style={{ color: "red" }}>{this.state.formErrors.working_days_count}</span>

            </FormGroup>
            <FormGroup>
              <Label for="file">file</Label>
              <Input type="file" id="file" onChange={this.onFileChange} />



            </FormGroup>
            <FormGroup>
              <Label for="type">file type </Label><br />
              <select name="types" onChange={(e) => {
                let { editCra } = this.state;

                editCra.document.type = e.target.value;

                this.setState({ editCra });

              }} >
                <option disabled selected value> -- select type -- </option>
                <option value={this.state.newCra.type}>pdf</option>
                <option value={this.state.newCra.type}>doc</option>
                <option value={this.state.newCra.type}>png</option>
                <option value={this.state.newCra.type}>png</option>
                <option value={this.state.newCra.type}>jpg</option>
              </select>

            </FormGroup>


          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.updateCra.bind(this)}>Update cra</Button>{' '}
            <Button color="secondary" onClick={this.toggleeditcramodal.bind(this)}>Cancel</Button>
          </ModalFooter>
        </Modal>


        <Table className="table table-bordered">
          <thead>
            <tr>

              <th>start date</th>
              <th>end date</th>
              <th>month</th>
              <th>working days count</th>


            </tr>
          </thead>

          <tbody>
            {cras}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default Second;
