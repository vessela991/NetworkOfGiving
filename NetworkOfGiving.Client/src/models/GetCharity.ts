export interface GetCharity{
    id:number,
    name: string,
    thumbnail: string,
    description: string,
    maxNumberOfParticipants: number,
    budgetRequired: number,
    donatedMoney : number,
    participants:number,
    username:string,
    userFullname:string
}