import { MiscelaneaPage } from './app.po';

describe('miscelanea App', () => {
  let page: MiscelaneaPage;

  beforeEach(() => {
    page = new MiscelaneaPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
