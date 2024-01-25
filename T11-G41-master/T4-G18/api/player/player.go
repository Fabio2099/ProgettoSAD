package player

import (
	"strconv"
	"time"

	"github.com/alarmfox/game-repository/model"
)

type Player struct {
	ID          int64      	`json:"id"`
	AccountID 	string     	`json:"accountId"`
	Name 		string		`json:"name"`
	CreatedAt  	time.Time   `json:"createdAt"`
	UpdatedAt   time.Time  	`json:"updatedAt"`
//	Turns      []Turn   	`json:"turns,omitempty"`
//	Games      []Game   	`json:"games,omitempty"`
	Wins        int64     	`json:"wins"`
	TurnsPlayed	int64		`json:"turnsPlayed"` //aggiunto
}

/*type Turns struct {
	ID        int64  `json:"id"`
	PlayerID string `json:"playerId"`
}*/



type KeyType int64

func (c KeyType) Parse(s string) (KeyType, error) {
	a, err := strconv.ParseInt(s, 10, 64)
	return KeyType(a), err
}

func (k KeyType) AsInt64() int64 {
	return int64(k)
}

type IntervalType time.Time

func (IntervalType) Parse(s string) (IntervalType, error) {
	t, err := time.Parse(time.DateOnly, s)
	return IntervalType(t), err
}

func (k IntervalType) AsTime() time.Time {
	return time.Time(k)
}
func(UpdateRequest) Validate() error{
	return nil
}
type UpdateRequest struct{
	Name	string	`json:"name"`
}
type AccountIdType string

func (AccountIdType) Parse(s string) (AccountIdType, error) {
	return AccountIdType(s), nil
}


func (a AccountIdType) AsString() string {
	return string(a)
}
func fromModel(p *model.Player) Player {
	return Player{
		ID:           	p.ID,
		AccountID:		p.AccountID,
		Name:			p.Name,
		CreatedAt:    	p.CreatedAt,
		UpdatedAt:    	p.UpdatedAt,

	//	Turns:     	 	parseTurns(g.Turns),
		Wins:			p.Wins,
		TurnsPlayed:	p.TurnsPlayed,
	}

}

/*func parseTurns(turns []model.Turn) []Turn {
	res := make([]Turn, len(turns))
	for i, turn := range turns {
		res[i] = Turn{
			ID:        	turn.ID,
			PlayerID: 	turn.PlayerID,
		}
	}
	return res
}*/