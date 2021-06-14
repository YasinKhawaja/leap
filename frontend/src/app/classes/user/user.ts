export class User {
    public id: string;
    public role: number;
    constructor(
        public firstname: string,
        public surname: string,
        public email: string,
        public username: string,
        public password: string,
    ) { }
}
