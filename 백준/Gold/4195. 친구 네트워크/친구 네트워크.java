import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {
    static Map<String, Party> parties;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(br.readLine());
            parties = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < f; j++) {
                String[] s = br.readLine().split(" ");
                String a = s[0];
                String b = s[1];
                Party network;
                if (!parties.containsKey(a) && !parties.containsKey(b)) {
                    Party party = new Party(a);
                    parties.put(a, party);
                    parties.put(b, party);
                    network = party;
                } else if (!parties.containsKey(a)) {
                    Party party = find(b);
                    party.size++;
                    parties.put(a, party);
                    network = party;
                } else if (!parties.containsKey(b)) {
                    Party party = find(a);
                    party.size++;
                    parties.put(b, party);
                    network = party;
                } else {
                    Party party1 = find(a);
                    Party party2 = find(b);
                    if (party1.leader.equals(party2.leader)) {
                        network = party1;
                    }
                    else if (party1.depth > party2.depth) {
                        party1.size += party2.size;
                        parties.put(party2.leader, party1);
                        party2.size = 0;
                        network = party1;
                    } else if (party1.depth == party2.depth) {
                        party1.size += party2.size;
                        parties.put(party2.leader, party1);
                        party2.size = 0;
                        party1.depth++;
                        network = party1;
                    } else {
                        party2.size += party1.size;
                        parties.put(party1.leader, party2);
                        party1.size = 0;
                        network = party2;
                    }
                }
                sb.append(network.size);
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }

    static Party find(String person) {
        Party party = parties.get(person);
        if (party.size == 0) {
            party = find(party.leader);
            parties.put(person, party);
        }
        return party;
    }

    static class Party {
        int size;
        String leader;
        int depth;

        public Party(String leader) {
            this.size = 2;
            this.leader = leader;
            this.depth = 0;
        }
    }
}
