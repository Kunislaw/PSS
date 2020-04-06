import { logging } from 'protractor';

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
    role: string[]
    delegations: string[]
}