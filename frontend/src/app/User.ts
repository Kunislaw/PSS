export interface User {
    id: number;
    companyName: string;
    companyAddress: string;
    companyNip: string;
    name: string;
    lastName: string;
    email: string;
    password: string;
    status: boolean;
    registrationData: Date;
    role: Set<string>;
    delegations: Array<string>;
}