package player

import (
	"github.com/alarmfox/game-repository/api"
  "github.com/alarmfox/game-repository/model"
	"gorm.io/gorm"
	"fmt"
)

type Repository struct {
	db *gorm.DB
}

func NewRepository(db *gorm.DB) *Repository {
	return &Repository{
		db: db,
	}
}

/*func (gs *Repository) FindById(id int64) (Player, error){
	var player model.Player
	err := gs.db.
			Preload("Players").
			First(&player,id).
			Error
		return fromModel(&player), api.MakeServiceError(err)
}*/


// FindAllPlayers recupera tutti i giocatori dal database
func (ps *Repository) FindAllPlayers() ([]Player, error){
	var players []Player
	if err := ps.db.Find(&players).Error; err!= nil{
		return nil, err
	}
	return players, nil
}

func(ps *Repository) Update(accountId string, r *UpdateRequest) (Player, error){
	fmt.Printf("Updating player with AccountID: %s, New Name: %s\n", accountId,r.Name)
	var(
		player model.Player = model.Player{AccountID: accountId}
		err error
	)

	err = ps.db.Model(&player).Where("account_id = ?",accountId).Updates(r).Error
	if err != nil {
		return Player{}, api.MakeServiceError(err)
	}

	return fromModel(&player), api.MakeServiceError(err)
}