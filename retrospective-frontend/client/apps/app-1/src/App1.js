/**
 * App1 is defined as
 * `<e-app-1>`
 *
 * Imperatively create application
 * @example
 * let app = new App1();
 *
 * Declaratively create application
 * @example
 * <e-app-1></e-app-1>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './app1.css';
import 'category-component';
import '@eui/draggable';
import '@eui/table';
import 'whatwg-fetch';

@definition('e-app-1', {
  style,
  props: {
    searchInput: { attribute: true, type: String },
    response: { attribute: false },
    searchItems: { type: JSON, default: [] },
  },
})


export default class App1 extends App {
  // Uncomment this block to add initialization code
  // constructor() {
  //   super();
  //   // initialize
  // }


  /**
   * Render the <e-app-1> app. This function is called each time a
   * prop changes.
   */
  columns = [
    { title: 'Glad', attribute: 'col1' },
    { title: 'Sad', attribute: 'col2' },
    { title: 'Mad', attribute: 'col3' },
  ];

  data = [
    {
      col1: html`
        
      <eui-draggable-v0-drop-area
          class='testGlad'
          border
          drop-area-title="GLAD"
          @add=${this}>
      </eui-draggable-v0-drop-area>`,

      col2: html`
      <eui-draggable-v0-drop-area
          border
          drop-area-title="SAD"
          @add=${this}>
      </eui-draggable-v0-drop-area>`,

      col3: html`
      <eui-draggable-v0-drop-area
          border
          drop-area-title="MAD"
          @add=${this}>
      </eui-draggable-v0-drop-area>`,
    },
  ];

  columnsCheck = [
    { title: 'GLAD', attribute: 'col1' },
    { title: 'SAD', attribute: 'col2' },
    { title: 'MAD', attribute: 'col3' }
  ];

  dataCheck = [
    { col1: 'Details 1', col2: 'Details 1', col3: 'Details 1'},
    { col1: 'Details 2', col2: 'Details 2', col3: 'Details 2'},
  ];

  data2 = [
    { col1: 'Details 1', col2: 'Details 1'},
    { col1: 'Details 2', col2: 'Details 2'},
    { col1: 'Details 3', col2: 'Details 3'},
    { col1: 'Details 4', col2: 'Details 4'},
  ]

  _onButtonClickedSearch(){
    const searchField = this.shadowRoot.querySelector('.search__text-field');
    const nameField = this.shadowRoot.querySelector('.name__text-field');
    fetch('http://localhost:8080/members/'+searchField.value)
    .then(function(response) {
      return response.json()
    }).then(function(json) {
      console.log('json')
      console.log(json)
      nameField.value = json.name
    }.bind(this)).catch(function(ex) {
      console.log('Parsing failed', ex)
    })
  }


  //example method
  // _onButtonClickedSearch(){
  //   const searchField = this.shadowRoot.querySelector('.search__text-field');
  //   console.log(searchField.value);
  //   const nameField = this.shadowRoot.querySelector('.name__text-field');
  //   // const categoryField = this.shadowRoot.querySelector('.category__text-field');
  //   // const engineerNameField = this.shadowRoot.querySelector('.engineer-name__text-field');
  //   // const engineerEmailField = this.shadowRoot.querySelector('.engineer-email__text-field');
  //   // const descriptionField = this.shadowRoot.querySelector('.description__text-field');
  //   // const dateValueField = this.shadowRoot.querySelector('.date__text-field');
  //   // const versionField = this.shadowRoot.querySelector('.versions__text-field');
  //   fetch('http://localhost:8080/members/'+searchField.value)
  //       // fetch('http://localhost:8080/ms/name/'+ searchField.value )
  //       // fetch('http://localhost:8080/ms/name/ms01' )
  //       .then(function(response) {
  //         return response.json()
  //       }).then(function(json) {
  //     console.log('json')
  //     console.log(json)
  //     this.searchItems = json
  //     console.log(this.searchItems)
  //     nameField.value = json.name
  //     console.log(nameField.value)
  //     // categoryField.value = json.category
  //     // engineerNameField.value = json.engineer.name
  //     // engineerEmailField.value = json.engineer.email
  //     // descriptionField.value = json.description
  //     // dateValueField.value = json.localDateTime.slice(0,10)
  //     // versionField.value =  json.versions
  //   }.bind(this)).catch(function(ex) {
  //     console.log('Parsing failed', ex)
  //   })
  // }

   _addr(){
    // find the table Element in the DOM...
    const tableElement = document.querySelector('eui-table-v0');
    const tab2 = this.shadowRoot.querySelector('.table__name');

    console.log(tab2)
// this is the row to add
    const rowToAdd = { col1: "Item 7", col2: "Details 7", col3: "Details 7", col4: "Details 7" };
    console.log(rowToAdd);

// add the new row by using the immutable pattern.
    tab2.data = [...tab2.data, rowToAdd];
  }


  _onButtonCreateMember(){
    //select text boxes
    const idField = this.shadowRoot.querySelector('.id__text-field');
    const nameField = this.shadowRoot.querySelector('.name__text-field');
    console.log(idField, nameField)
    fetch('http://localhost:8080/members/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: idField.value,
        name: nameField.value,
      })
    })
    alert(`${nameField.value} created`)
    //clear content of text boxes after submission
    idField.value = ''
    namefield.value = ''
  }
  _onButtonGetAllMembers(){
    fetch('http://localhost:8080/members/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: '3000',
        name: 'JavaScript',
      })
    })
  }

  render() {




    function _addGlad() {
      console.log('in here');
      // const card = document.createElement('eui-layout-v0-card');
      // card.cardTitle = 'This is the title';
      // const parent = document.getElementById('cards');
      // console.log(card);
      // console.log(parent);
      // const p2 = this.shadowRoot.querySelector('#cards');
      // console.log(p2);
      document.getElementById('cards').innerHTML += '<h1>test</h1>';
    }
    return html`
      
      <div class="team-search">
        <eui-base-v0-textarea class="search__text-field" placeholder="Enter Team"></eui-base-v0-textarea>
        
      </div>
      
      </br></br>
      
      <div primary class="search-button">
        <eui-base-v0-button  @click="${() => this._onButtonClickedSearch()}" >Search</eui-base-v0-button>
        
      </div>

      <div class="category-buttons">
        <eui-base-v0-button @click="${() => _addGlad()}" primary>ADD GLAD</eui-base-v0-button>
        <eui-base-v0-button primary>ADD SAD</eui-base-v0-button>
        <eui-base-v0-button primary>ADD MAD</eui-base-v0-button>
      </div>
      
      <hr>
      <eui-table-v0
          class="table__name"
          .columns=${(this.columnsCheck)}
          .data=${this.dataCheck}
          resizable
          border
          striped
      >
      </eui-table-v0>
      
      <div class="create-member">
        <eui-base-v0-textarea class="id__text-field" placeholder="Enter Id"></eui-base-v0-textarea>
        <eui-base-v0-textarea class="name__text-field" placeholder="Enter Name"></eui-base-v0-textarea>
        <eui-base-v0-button  @click="${() => this._onButtonCreateMember()}" >create member</eui-base-v0-button>
      </div>
      <eui-base-v0-button  @click="${() => this._addr()}" >add row</eui-base-v0-button>
      <eui-base-v0-textarea class="display-names" placeholder="Enter Name"></eui-base-v0-textarea>

      
    `;
  }
}

/**
 * Register the component as e-app-1.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// App1.register();
