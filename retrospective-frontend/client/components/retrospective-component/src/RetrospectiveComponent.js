/**
 * Component RetrospectiveComponent is defined as
 * `<e-retrospective-component>`
 *
 * Imperatively create component
 * @example
 * let component = new RetrospectiveComponent();
 *
 * Declaratively create component
 * @example
 * <e-retrospective-component></e-retrospective-component>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './retrospectiveComponent.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-retrospective-component', {
  style,
  home: 'retrospective-component',
  props: {
    propOne: { attribute: true, type: Boolean },
    propTwo: { attribute: true, type: String, default: 'Hello World' },
  },
})
export default class RetrospectiveComponent extends LitComponent {
  /**
   * Render the <e-retrospective-component> component. This function is called each time a
   * prop changes.
   */
  render() {
    return html`<h1>Your component markup goes here</h1>
    `;
  }
}

/**
 * Register the component as e-retrospective-component.
 * Registration can be done at a later time and with a different name
 */
RetrospectiveComponent.register();
