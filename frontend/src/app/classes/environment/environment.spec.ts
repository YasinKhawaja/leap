import { Environment } from './environment';

describe('Environment', () => {
    it('should create an instance', () => {
        // Given
        var expName = 'Test';

        // When
        var actEnv = new Environment('Test');

        // Then
        expect(actEnv.name).toBe(expName);
    });
});