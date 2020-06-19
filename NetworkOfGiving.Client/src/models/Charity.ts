export interface Charity{ 
    id: number;
    name: string;
    thumbnail: Blob;
    description: string;
    maxNumberOfParticipants: number;
    budgetRequired: number
}