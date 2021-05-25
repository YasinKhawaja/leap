/////// Protractor end-to-end testing CRUD environment ///////
describe('CRUD environment', function () {

    // CREATE an environment
    it('should CREATE an environment', function () {
        browser.get('http://localhost:4200/environments/create'); // HTML to test

        // Given
        var nameInput = element(by.id('name'));
        var createBtn = element(by.id('create'));
        var resultPopup = element(by.id('swal2-content'));

        // When
        nameInput.sendKeys('Test');
        createBtn.click();

        // Then
        expect(resultPopup.getText()).toBe("Environment Test created.")
    });

    // GET (READ) an environment
    it('should GET an environment', function () {
        browser.get('http://localhost:4200/environments'); // HTML to test

        // Given


        // When


        // Then

    });

    // UPDATE an environment
    it('should UPDATE an environment', function () {
        browser.get('http://localhost:4200/environments/299/update'); // HTML to test

        // Given


        // When


        // Then

    });

    // DELETE an environment
    it('should DELETE an environment', function () {
        browser.get('http://localhost:4200/environments/301/delete'); // HTML to test

        // Given
        var deleteBtn = element(by.id('delete'));

        // When
        deleteBtn.click();

        // Then
        
    });
});