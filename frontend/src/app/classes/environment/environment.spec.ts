import { Environment } from './environment';

describe('Environment', () => {
    it('should create an instance', () => {
        // Given
        var expEnv;

        // When
        expEnv = new Environment('Test');

        // Then
        expect(expEnv.name).toBe('Test');
    });
});