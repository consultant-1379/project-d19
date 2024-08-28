/**
 * Component CategoryComponent is defined as
 * `<e-category-component>`
 *
 * Imperatively create component
 * @example
 * let component = new CategoryComponent();
 *
 * Declaratively create component
 * @example
 * <e-category-component></e-category-component>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './categoryComponent.css';

import '@eui/draggable';
import '@eui/table';

function _addGlad() {
  const card = document.createElement('eui-layout-v0-card');
  card.cardTitle = 'This is the title';

  console.log(card);
  this.shadowRoot.querySelector('#testGlad').appendChild(card);
  // const data = [
  //   {
  //     col1: html`
  //     <eui-draggable-v0-drop-area
  //         class='testGlad'
  //         border
  //         drop-area-title="GLAD"
  //         @add=${this}>
  //       <eui-base-v0-button
  //           draggable="false"
  //           @click="${() => _addGlad()}"
  //           primary
  //       >ADD GLAD</eui-base-v0-button>
  //       <eui-layout-v0-card card-title="Card One" drag></eui-layout-v0-card>
  //     </eui-draggable-v0-drop-area>`,
  //     col2: html`
  //     <eui-draggable-v0-drop-area
  //         border
  //         drop-area-title="SAD"
  //         @add=${this}>
  //       <eui-base-v0-button primary>ADD SAD</eui-base-v0-buttonprimary>
  //     </eui-draggable-v0-drop-area>`,
  //     col3: html`
  //     <eui-draggable-v0-drop-area
  //         border
  //         drop-area-title="MAD"
  //         @add=${this}>
  //       <eui-base-v0-button primary>ADD MAD</eui-base-v0-button>
  //     </eui-draggable-v0-drop-area>`,
  //   },
  // ];
  //
  // parent.data = [...parent.data, data];
}

const columns = [
  { title: 'Glad', attribute: 'col1' },
  { title: 'Sad', attribute: 'col2' },
  { title: 'Mad', attribute: 'col3' },
];
const data = [
  {
    col1: html`
      <eui-draggable-v0-drop-area
          id='testGlad'
          border
          drop-area-title="GLAD"
          @add=${this}>
        <eui-base-v0-button
            draggable="false"
            @click="${() => _addGlad()}"
            primary
        >ADD GLAD</eui-base-v0-button>
        
        <eui-layout-v0-card card-title="Card One" drag></eui-layout-v0-card>
      </eui-draggable-v0-drop-area>`,
    col2: html`
      <eui-draggable-v0-drop-area
          border
          drop-area-title="SAD"
          @add=${this}>
        <eui-base-v0-button primary>ADD SAD</eui-base-v0-buttonprimary>
      </eui-draggable-v0-drop-area>`,
    col3: html`
      <eui-draggable-v0-drop-area
          border
          drop-area-title="MAD"
          @add=${this}>
        <eui-base-v0-button primary>ADD MAD</eui-base-v0-button>
      </eui-draggable-v0-drop-area>`,
  },
];


/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-category-component', {
  style,
  home: 'category-component',
  props: {},
})
export default class CategoryComponent extends LitComponent {
  /**
   * Render the <e-category-component> component. This function is called each time a
   * prop changes.
   */
  handleEvent(event) {
    // handle the addition of an item to the drop area
    if (event.type === 'add') {
      console.log(`Card Title: ${event.item.cardTitle}`);
      // Card Title: Card One

      console.log(`Title of destination drop area: ${event.to.dropAreaTitle}`);
      // Title of destination drop area: Drop Area Two

      console.log(`Title of originating drop area: ${event.from.dropAreaTitle}`);
      // Title of originating drop area: Drop Area One
    }
  }

  render() {
    return html`
      <eui-table-v0
          .columns=${columns}
          .data=${data}
          resizable
          no-header
          border
          custom-row-height="250"
      >
      </eui-table-v0>
      
    `;
  }
}

/**
 * Register the component as e-category-component.
 * Registration can be done at a later time and with a different name
 */
CategoryComponent.register();
