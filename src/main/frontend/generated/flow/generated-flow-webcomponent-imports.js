import { injectGlobalWebcomponentCss } from 'Frontend/generated/jar-resources/theme-util.js';

import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/side-nav/src/vaadin-side-nav.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/integer-field/src/vaadin-integer-field.js';
import '@vaadin/date-picker/src/vaadin-date-picker.js';
import 'Frontend/generated/jar-resources/datepickerConnector.js';
import '@vaadin/dialog/src/vaadin-dialog.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/app-layout/src/vaadin-app-layout.js';
import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/app-layout/src/vaadin-drawer-toggle.js';
import '@vaadin/upload/src/vaadin-upload.js';
import '@vaadin/side-nav/src/vaadin-side-nav-item.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/multi-select-combo-box/src/vaadin-multi-select-combo-box.js';
import '@vaadin/button/src/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/scroller/src/vaadin-scroller.js';
import '@vaadin/custom-field/src/vaadin-custom-field.js';
import '@vaadin/notification/src/vaadin-notification.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '00723409cff18397c2c9aaac71c7e56929378de26a1f5675a48585ea16adf105') {
    pending.push(import('./chunks/chunk-37aa5dc206c000bc352e634b59ec7ef493615040a7c23c00ad5f41d7c599998f.js'));
  }
  if (key === '6a3dfbf2c6b2f6f98108ce1678cc4ca97f96b9076d5ab5c39e05f2c95572eddc') {
    pending.push(import('./chunks/chunk-2b85016f1cabb717e4158dae3c8677ae41f9291654ee67baa2145b62651a1f1d.js'));
  }
  if (key === '41631fdfd4f58d493876501be267ffb49b87f9312ae023f5a4ae47ed88ffacf5') {
    pending.push(import('./chunks/chunk-2b85016f1cabb717e4158dae3c8677ae41f9291654ee67baa2145b62651a1f1d.js'));
  }
  if (key === 'c48be396d1604a7bd28b8fba6a5d89ce07e4d5414899a724631c1af5bba0a354') {
    pending.push(import('./chunks/chunk-d1b5499024a33708806427d293dde86000bb4199a434493072301c5a152461f8.js'));
  }
  if (key === '0d21a18ae72933f1c05709cebb27f442e2f83e97576ba0f700975a9a746ff753') {
    pending.push(import('./chunks/chunk-bd6f334dca169fca68bc1d877a4e929b975b9610a3b5e68a7b5d930ddec6a55d.js'));
  }
  if (key === '0834c54eb8aab93e5d68859644552d30f1b8e55672194519d46150d0d4425cd6') {
    pending.push(import('./chunks/chunk-bd6f334dca169fca68bc1d877a4e929b975b9610a3b5e68a7b5d930ddec6a55d.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}