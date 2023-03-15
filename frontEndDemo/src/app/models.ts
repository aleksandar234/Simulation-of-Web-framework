export interface Users {
  id: number,
  firstName: string,
  lastName: string,
  username: number,
  password: string,
  permissions: Permission[],
  userId: number
}

export interface UserInsert {
  firstName: string,
  lastName: string,
  username: string,
  password: string,
  permissions: Permission[]
}

export interface Permission {
  permission: string
}

export interface UserLogin {
  username: string,
  password: string
}

export interface Machine {
  name: string,
  status: string,
  createdBy: string,
  active: boolean,
  angularDate: string
}

export interface ErrorMassage {
      date: Date,
      machineId: number,
      reservedOperation: String,
      errorMassage: String,
      users: Users
}


