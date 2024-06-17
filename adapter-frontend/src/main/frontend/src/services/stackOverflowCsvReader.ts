import ResultSetForYear from '../model/resultSetForYear'
import ControlState from '../model/controlState'
import { ApolloClient, InMemoryCache } from '@apollo/client';
import { RestLink } from 'apollo-link-rest';
import { gql } from '@apollo/client';

// const query = gql`
//   query Luke {
//     person @rest(type: "Person", path: "people/1/") {
//       name
//     }
//   }
// `;

const query = gql`
  query Luke {
    person @rest(type: "Person", path: "people/1/") {
      name
    }
  }
`;


// https://medium.com/november-five/using-apollo-client-with-a-rest-api-ad0203a807cd
export default class SurveyEntryService {
    // TODO: Configure somehow via springs yml or zookeeper..
    static readonly SERVER_URL = "localhost:9999"
    restLink: RestLink
    // TODO: get rid of any..
    client: ApolloClient<any>

    constructor() {
        this.restLink = new RestLink({ uri: SurveyEntryService.SERVER_URL + "/api/" });
        this.client = new ApolloClient({
            cache: new InMemoryCache(),
            link: this.restLink
          });
    }
    
    public getSurveyEntries(filter: ControlState): Promise<ResultSetForYear[]> {
        // Invoke the query and log the person's name
        return this.client.query({ query }).then(response => {
            console.log(response.data.person.name);
            return response.data
        });
    }

}