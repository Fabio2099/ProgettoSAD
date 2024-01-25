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

/*func (gc *Controller) FindByID(w http.ResponseWriter, r *http.Request) error {
	id, err := api.FromUrlParams[KeyType](r, "id")
	if err != nil {
		return err
	}
	g, err := gc.service.FindByID(id.AsInt64())

	if err != nil {
		return api.MakeHttpError(err)
	}

	return api.WriteJson(w, http.StatusOK, g)
}

func (gc *Controller) List(w http.ResponseWriter, r *http.Request) error {
	id, err := api.FromUrlQuery[AccountIdType](r, "Id", "")

	if err != nil {
		return err
	}
	page, err := api.FromUrlQuery[KeyType](r, "page", 1)

	if err != nil {
		return err
	}

	pageSize, err := api.FromUrlQuery[KeyType](r, "pageSize", 10)

	if err != nil {
		return err
	}*/

	/*startDate, err := api.FromUrlQuery(r, "startDate", IntervalType(time.Now().Add(-24*time.Hour)))

	if err != nil {
		return err
	}

	endDate, err := api.FromUrlQuery(r, "endDate", IntervalType(time.Now()))

	if err != nil {
		return err
	}

	ip := api.IntervalParams{
		Start: startDate.AsTime(),
		End:   endDate.AsTime(),
	}*/

	/*pp := api.PaginationParams{
		Page:     page.AsInt64(),
		PageSize: pageSize.AsInt64(),
	}*/

	/*players, err := gc.service.FindByID(id.AsInt64())
	if err != nil {
		return api.MakeHttpError(err)
	}
	return api.WriteJson(w, http.StatusOK, players)
}*/

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