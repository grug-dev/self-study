import { HeroesappfirebasePage } from './app.po';

describe('heroesappfirebase App', () => {
  let page: HeroesappfirebasePage;

  beforeEach(() => {
    page = new HeroesappfirebasePage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
