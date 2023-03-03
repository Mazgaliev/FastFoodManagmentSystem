import {WorkerId} from "./WorkerId";
import {PersonRole} from "./PersonRole";

export interface Person {
  id: WorkerId;
  username: string;
  role: PersonRole | null;

}
