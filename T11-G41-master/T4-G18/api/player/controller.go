package player

import (
	"net/http"
	//"os"
	"github.com/alarmfox/game-repository/api"
	"strconv"
)

type Service interface {
	FindAllPlayers() ([]Player, error)
	Update(accountId string, request *UpdateRequest) (Player, error)
}
type Controller struct {
	service Service
}



func NewController(ps Service) *Controller {
	return &Controller{service: ps}
}


// ListPlayers restituisce tutti i giocatori
func (pc *Controller) ListPlayers(w http.ResponseWriter, r *http.Request) error{
	players,err := pc.service.FindAllPlayers()
	if err != nil{
		return err
	}
	return api.WriteJson(w, http.StatusOK, players)
}

func(pc *Controller) Update(w http.ResponseWriter, r *http.Request) error{
	var accountId KeyType
	accountId, err := api.FromUrlParams[KeyType](r, "accountId")
	if err != nil{
		return err
	}

	request, err := api.FromJsonBody[UpdateRequest](r.Body)
	if err!= nil{
		return err
	}
	accountIdString := strconv.FormatInt(int64(accountId), 10)
	player, err := pc.service.Update(accountIdString, &request)
	if err != nil{
		return api.MakeHttpError(err)
	}

	return api.WriteJson(w, http.StatusOK, player)
}